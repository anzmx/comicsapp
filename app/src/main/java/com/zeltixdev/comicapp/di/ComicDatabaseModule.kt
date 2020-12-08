package com.zeltixdev.comicapp.di

import android.app.Application
import com.zeltixdev.comicapp.data.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ComicDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = AppDatabase.getDatabase(application)

    @Singleton
    @Provides
    fun provideComicDao(database: AppDatabase) = database.getComicDao()
}