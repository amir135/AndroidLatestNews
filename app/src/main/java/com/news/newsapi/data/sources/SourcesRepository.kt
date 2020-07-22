package com.news.newsapi.data.sources

import android.util.Log
import androidx.lifecycle.LiveData
import com.news.newsapi.ConstantVariables
import com.news.newsapi.api.BaseDataSource
import com.news.newsapi.api.NewsService
import com.news.newsapi.api.servicemodel.SourcesModel
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SourcesRepository @Inject constructor(private val dao: SourcesDao, val service: NewsService): BaseDataSource() {

    fun getAllSources(): LiveData<List<Sources>> {
        val callable: Callable<LiveData<List<Sources>>> = Callable<LiveData<List<Sources>>> {
            return@Callable  dao.getAllResource()
        }
        return Executors.newSingleThreadExecutor().submit(callable).get()
    }
    fun getSourcesCount(): Int {
        val callable: Callable<Int> = Callable<Int> {
            return@Callable  dao.getResourceCount()
        }
        return Executors.newSingleThreadExecutor().submit(callable).get()
    }

    fun fetch() {

        var sourcesList = fetchSource()
        sourcesList.let {
            if(sourcesList!!.status == "ok")
            {
                if(sourcesList.sources.size>0)
                {
                    val callable: Callable<Int> = Callable<Int> {
                        dao.insertAll(sourcesList.sources)
                        null
                    }
                    Executors.newSingleThreadExecutor().submit(callable).get()
                }
                else{
                    Timber.d("Sources is blank!!")
                   // Log.d(ConstantVariables.TAG_NAME,"Sources is blank!!")

                }
            }
            else{
                Timber.d("Fetch Sources Error!!")
                //Log.e(ConstantVariables.TAG_NAME,"fetch Sources Error!!")
            }
        }
    }

    private fun fetchSource(): SourcesModel? {
       return  runBlocking {
           getResult {service.getAllSources()}.data
        }
    }
}