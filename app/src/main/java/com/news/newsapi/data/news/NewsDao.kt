package com.news.newsapi.data.news

import androidx.lifecycle.LiveData
import androidx.room.*
import com.news.newsapi.data.Dto.NewsSource


@Dao
interface NewsDao {

//    @Query("SELECT * FROM news inner join sources on news.sourceId = sources.id order by news.publishedAt desc")
//    fun getAllNews(): LiveData<List<News>>
//    @Query("SELECT * FROM news order by publishedAt desc")
//    fun getAllNews(): LiveData<List<News>>

    @Query("SELECT COUNT(*) FROM news")
    fun getNewsCount(): Int

    @Query("SELECT news.*, sources.name FROM news INNER JOIN sources ON news.sourceId = sources.id order by news.publishedAt desc")
    fun getAllNews(): LiveData<List<NewsSource>>

    @Query("SELECT * FROM news")
    fun getNews(): LiveData<List<News>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: ArrayList<News>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News)

    @Update (onConflict = OnConflictStrategy.REPLACE)
    fun update(news: News)

    @Delete
    fun delete(news: News)
}