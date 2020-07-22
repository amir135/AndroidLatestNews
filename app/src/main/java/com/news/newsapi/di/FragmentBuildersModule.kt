package com.news.newsapi.di

import com.news.newsapi.fragment.Sources.SourcesFragment
import com.news.newsapi.fragment.news.NewsFragment
import com.news.newsapi.fragment.newsviewer.NewsViewerFragment


import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeUnitsFragment(): SourcesFragment
    @ContributesAndroidInjector
    abstract fun contributeNewsFragment(): NewsFragment
    @ContributesAndroidInjector
    abstract fun contributeNewsViewerFragment(): NewsViewerFragment
}
