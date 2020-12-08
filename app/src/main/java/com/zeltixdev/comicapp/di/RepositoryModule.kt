package com.zeltixdev.comicapp.di

import com.zeltixdev.comicapp.repository.ComicRepository
import com.zeltixdev.comicapp.repository.impl.ComicRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideComicsRepository(repository: ComicRepositoryImpl): ComicRepository = repository
}