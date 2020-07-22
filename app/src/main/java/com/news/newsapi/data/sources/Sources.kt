package com.news.newsapi.data.sources

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "sources")
data class Sources (
    @PrimaryKey()
    @field:SerializedName("id")
    var id: String,
    @field:SerializedName("name")
    var name: String,
    @field:SerializedName("description")
    var sourceDescription: String?,
    @field:SerializedName("url")
    var sourceUrl: String?,
    @field:SerializedName("category")
    var category: String?,
    @field:SerializedName("language")
    var language: String?,
    @field:SerializedName("country")
    var country: String?
)