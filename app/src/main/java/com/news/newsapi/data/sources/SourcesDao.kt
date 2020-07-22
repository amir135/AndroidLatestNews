package com.news.newsapi.data.sources

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface SourcesDao {

    @Query("SELECT * FROM sources order by name asc")
    fun getAllResource(): LiveData<List<Sources>>

    @Query("SELECT COUNT(id) FROM sources")
    fun getResourceCount(): Int

    @Query("SELECT * FROM sources where id=:id")
    fun exist(id:String): Sources?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(resources: ArrayList<Sources>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(resource: Sources)

    @Update (onConflict = OnConflictStrategy.REPLACE)
    fun update(resource: Sources)

    @Delete
    fun delete(resource: Sources)
}