package com.news.newsapi.api.servicemodel

import com.google.gson.annotations.SerializedName

data class SourceDto(
    @field:SerializedName("id")
    var id: String?,
    @field:SerializedName("name")
    var name: String?
)