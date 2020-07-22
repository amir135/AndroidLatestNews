package com.news.newsapi.api.servicemodel

import com.google.gson.annotations.SerializedName
import com.news.newsapi.data.sources.Sources

data class SourcesModel(
    @field:SerializedName("status")
    var status: String,
    @field:SerializedName("sources")
    var sources: ArrayList<Sources>
)