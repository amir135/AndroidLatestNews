package com.news.newsapi.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.news.newsapi.fragment.Sources.SourcesViewModel
import com.news.newsapi.fragment.news.NewsViewModel
import com.news.newsapi.fragment.newsviewer.NewsViewerViewModel


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(SourcesViewModel::class)
    abstract fun bindSourcesViewModel(viewModel: SourcesViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewModel::class)
    abstract fun bindNewsViewModel(viewModel: NewsViewModel): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey(NewsViewerViewModel::class)
    abstract fun bindNewsViewerViewModel(viewModel: NewsViewerViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
