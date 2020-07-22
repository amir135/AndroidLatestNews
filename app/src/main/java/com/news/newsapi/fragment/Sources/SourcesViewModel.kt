package com.news.newsapi.fragment.Sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.news.newsapi.api.NewsService
import com.news.newsapi.data.sources.SourcesRepository
import com.news.newsapi.data.sources.Sources
import javax.inject.Inject

class SourcesViewModel  @Inject constructor(private val repository: SourcesRepository, val service: NewsService): ViewModel() {

    fun getSources(): LiveData<List<Sources>> {
      return  repository.getAllSources()
    }

    fun getSourcesCount(): Int {
      return  repository.getSourcesCount()
    }

    fun fetchSources() {

            repository.fetch()


    }
}