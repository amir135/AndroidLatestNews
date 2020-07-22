package com.news.newsapi.data.news

import androidx.lifecycle.LiveData
import com.news.newsapi.api.BaseDataSource
import com.news.newsapi.api.NewsService
import com.news.newsapi.api.servicemodel.NewsModel
import com.news.newsapi.data.Dto.NewsSource
import com.news.newsapi.data.sources.Sources
import com.news.newsapi.data.sources.SourcesDao
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(private val dao: NewsDao, private val daoSource: SourcesDao, val service: NewsService): BaseDataSource() {

    fun getAllNews(): LiveData<List<NewsSource>> {
        val callable: Callable<LiveData<List<NewsSource>>> = Callable<LiveData<List<NewsSource>>> {
            return@Callable  dao.getAllNews()
        }
        return Executors.newSingleThreadExecutor().submit(callable).get()
    }
    fun getNewsCount(): Int {
        val callable: Callable<Int> = Callable<Int> {
            return@Callable  dao.getNewsCount()
        }
        return Executors.newSingleThreadExecutor().submit(callable).get()
    }
    fun existSource(id:String): Sources? {
        val callable: Callable<Sources?> = Callable<Sources?> {
            return@Callable  daoSource.exist(id)
        }
        return Executors.newSingleThreadExecutor().submit(callable).get()
    }

    fun fetch(q:String?,language:String?,country:String?) {
            var xx=getNewsCount()
            var query = "a"
            if (q != null && q != "")
                query = q
            val newsList = fetchNews(query, language, country)
            newsList.let {
                if (newsList!!.status == "ok") {
                    if (newsList.news.size > 0) {
                        newsList.news.forEach {
                            if (it.source != null) {
                                if (it.source.id == null) {
                                    it.source.id = it.source.name?.replace(" ", "_")
                                }
                                if (existSource(it.source.id!!) == null) {
                                    val source = Sources(
                                        id = it.source.id!!,
                                        name = it.source.name!!,
                                        sourceDescription = null,
                                        sourceUrl = null,
                                        category = null,
                                        country = null,
                                        language = null
                                    )
                                    val callable: Callable<Sources?> = Callable<Sources?> {
                                        daoSource.insert(source)
                                        null
                                    }
                                    Executors.newSingleThreadExecutor().submit(callable).get()

                                }
                                it.sourceId = it.source.id
                            }
                        }
                        val callable: Callable<Sources?> = Callable<Sources?> {
                            dao.insertAll(newsList.news)
                            null
                        }
                        Executors.newSingleThreadExecutor().submit(callable).get()

                    } else {
                        //Log.d(ConstantVariables.TAG_NAME, "Sources is blank!!")
                       Timber.d("Sources is blank!!")
                    }
                } else {

                    //Log.e(ConstantVariables.TAG_NAME, "fetch Sources Error!!")
                    Timber.d("fetch Sources Error!!")
                }
            }

    }

    private fun fetchNews(q:String, language:String?, country:String?): NewsModel? {
      return runBlocking {
           val now: Calendar = Calendar.getInstance()
           now.add(Calendar.DAY_OF_YEAR,-1)
           val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            getResult { service.getNews(q, sdf.format(now.time), "", "") }.data
        }
    }
}