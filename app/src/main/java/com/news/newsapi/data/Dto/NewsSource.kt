package com.news.newsapi.data.Dto

import androidx.room.Embedded
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName
import com.news.newsapi.data.news.News
import com.news.newsapi.data.sources.Sources
import java.util.*


data  class NewsSource (
    @field:SerializedName("sourceId")
    var sourceId: String?,
    @field:SerializedName("author")
    var author: String?,
    @field:SerializedName("title")
    var title: String,
    @field:SerializedName("description")
    var description: String?,
    @field:SerializedName("url")
    var url: String,
    @field:SerializedName("urlToImage")
    var urlToImage: String?,
    @field:SerializedName("publishedAt")
    var publishedAt: Date,
    @field:SerializedName("content")
    var content: String?,
    @field:SerializedName("name")
    var name: String
)