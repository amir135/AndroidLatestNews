package com.news.newsapi.api.servicemodel

import com.google.gson.annotations.SerializedName
import com.news.newsapi.data.news.News
import com.news.newsapi.data.sources.Sources

data class NewsModel(
    @field:SerializedName("status")
    var status: String,
    @field:SerializedName("totalResults")
    var totalResults: Int,
    @field:SerializedName("articles")
    var news: ArrayList<News>
)