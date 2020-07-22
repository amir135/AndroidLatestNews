package com.news.newsapi.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.news.newsapi.data.news.NewsDao
import com.news.newsapi.data.sources.SourcesDao
import com.news.newsapi.util.*
import kotlinx.coroutines.runBlocking
import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsDaoTest : DbTest() {
    private lateinit var newsDao: NewsDao
    private lateinit var sourcesDao: SourcesDao
    private val newsA = testNewsA.copy()
    private val newsB = testNewsB.copy()
    private val sourcesA = testSourcesB.copy()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() {
        newsDao = db.newsDao()
        sourcesDao = db.sourcesDao()

        // Insert database in non-alphabetical order to test that results are sorted by name
        runBlocking {
            sourcesDao.insert( sourcesA)
            newsDao.insertAll( arrayListOf(newsA, newsB))
        }
    }

    @Test fun testGetSets() {

        val list = getValue(newsDao.getNews())
        assertThat(list.size, equalTo(2))

        // Ensure database list is sorted by name
        assertThat(list[0], equalTo(newsA))
        assertThat(list[1], equalTo(newsB))
    }


}