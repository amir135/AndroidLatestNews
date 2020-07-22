package com.news.newsapi.data.news

import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.news.newsapi.api.servicemodel.SourceDto
import com.news.newsapi.data.sources.Sources
import java.util.*




@Entity(tableName = "news",foreignKeys = [ForeignKey(
    entity = Sources::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("sourceId"),
    onDelete = ForeignKey.CASCADE
)]
)
data class News (



    @field:SerializedName("sourceId")
    var sourceId: String?,
    @field:SerializedName("author")
    var author: String?,
    @field:SerializedName("title")
    var title: String,
    @field:SerializedName("description")
    var description: String?,
    @PrimaryKey
    @field:SerializedName("url")
    var url: String,
    @field:SerializedName("urlToImage")
    var urlToImage: String?,
    @field:SerializedName("publishedAt")
    @ColumnInfo( index = true)
    var publishedAt: Date,
    @field:SerializedName("content")
    var content: String?

){
    @Ignore
    @SerializedName("source") val source: SourceDto?=null
    @Ignore
    var sources :Sources?=null
}

