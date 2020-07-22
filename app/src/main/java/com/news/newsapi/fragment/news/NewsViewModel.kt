package com.news.newsapi.fragment.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.news.newsapi.api.NewsService
import com.news.newsapi.data.Dto.NewsSource
import com.news.newsapi.data.news.News
import com.news.newsapi.data.news.NewsRepository
import com.news.newsapi.data.sources.SourcesRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.intellij.lang.annotations.Language
import javax.inject.Inject

class NewsViewModel  @Inject constructor(private val repository: NewsRepository,private val repositorySources: SourcesRepository): ViewModel(){
    fun getAllNews(): LiveData<List<NewsSource>> {
        return  repository.getAllNews()
    }
    fun fetchNews(q:String?,language: String?,country:String?) {
        repository.fetch(q,language,country)
    }
    fun fetchSources() {
        repositorySources.fetch()
    }
}