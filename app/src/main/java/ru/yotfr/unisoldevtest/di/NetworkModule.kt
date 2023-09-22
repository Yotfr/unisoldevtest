package ru.yotfr.unisoldevtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.datasource.remote.api.WallpaperApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideWallpaperApi(): WallpaperApi = WallpaperApi()

}