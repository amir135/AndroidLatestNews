package com.news.newsapi.worker


import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.news.newsapi.ConstantVariables.DATA_FILENAME
import com.news.newsapi.data.AppDatabase
import com.news.newsapi.data.news.News
import com.news.newsapi.data.sources.Sources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import timber.log.Timber

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {
        withContext(Dispatchers.IO) {

            try {
                applicationContext.assets.open(DATA_FILENAME).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val type = object : TypeToken<List<News>>() {}.type
                        val typeSources = object : TypeToken<List<Sources>>() {}.type
                        val listSource: ArrayList<Sources> = Gson().fromJson(jsonReader, typeSources)
                        val list: ArrayList<News> = Gson().fromJson(jsonReader, type)

                         AppDatabase.getInstance(applicationContext).sourcesDao().insertAll(listSource)
                         AppDatabase.getInstance(applicationContext).newsDao().insertAll(list)

                        Result.success()
                    }
                }
            } catch (e: Exception) {
                Timber.e(e, "Error seeding database")
                Result.failure()
            }
        }
    }
}