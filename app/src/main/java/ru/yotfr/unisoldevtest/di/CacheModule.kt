package ru.yotfr.unisoldevtest.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.paging.pagingcache.WallpapersCache
import ru.yotfr.unisoldevtest.data.paging.pagingcache.WallpapersCacheImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {
    @Provides
    @Singleton
    fun provideWallpapersCache(): WallpapersCache {
        return WallpapersCacheImpl()
    }
}