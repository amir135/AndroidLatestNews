package com.news.newsapi.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.news.newsapi.data.news.NewsRepository
import com.news.newsapi.data.sources.SourcesRepository
import com.news.newsapi.fragment.news.NewsViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class NewsViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val newsRepository = mock(NewsRepository::class.java)

    private val  sourcesRepository= mock(SourcesRepository::class.java)

    private var viewModel =
       NewsViewModel(
           newsRepository,
           sourcesRepository
        )

    @Test
    fun doNotFetchWithoutObservers() {

        verify(newsRepository, never()).getAllNews()
    }
    @Test
    fun doNotFetchWithoutObserverstypes() {

        verify(sourcesRepository, never()).getAllSources()
    }
}