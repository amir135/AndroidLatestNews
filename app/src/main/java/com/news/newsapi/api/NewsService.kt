package com.news.newsapi.api

import com.news.newsapi.ConstantVariables
import com.news.newsapi.api.servicemodel.*
import retrofit2.Response
import retrofit2.http.*

/**
 * Lego REST API access points
 */
interface NewsService {

    companion object {
        const val ENDPOINT = ConstantVariables.baseUrl
        const val API_KEY = ConstantVariables.apiKey
    }

    @GET("sources?apiKey=$API_KEY")
    suspend fun getAllSources(): Response<SourcesModel>


    @GET("everything?apiKey=$API_KEY&sortBy=publishedAt")
    suspend fun getNews(@Query("q") q:String,@Query("from")  from: String,@Query("language")language: String?,@Query("country")country:String?): Response<NewsModel>

    @GET("top-headlines?apiKey=$API_KEY&sortBy=publishedAt&country=us")
    suspend fun getHeadlinesNews(): Response<NewsModel>


}
