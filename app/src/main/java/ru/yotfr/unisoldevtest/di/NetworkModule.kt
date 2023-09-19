package ru.yotfr.unisoldevtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.datasource.remote.api.WallpaperApi

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideWallpaperApi(): WallpaperApi = WallpaperApi()

}