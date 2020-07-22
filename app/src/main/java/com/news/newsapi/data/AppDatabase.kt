package com.news.newsapi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.news.newsapi.data.news.News
import com.news.newsapi.data.news.NewsDao
import com.news.newsapi.data.sources.Sources
import com.news.newsapi.data.sources.SourcesDao

/**
 * The Room database for this app
 */
@Database(entities = [Sources::class, News::class],
        version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun sourcesDao(): SourcesDao
    abstract fun newsDao(): NewsDao


    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }


        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java, "newsDB"
            ).build()

        }

    }
}
