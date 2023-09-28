package ru.yotfr.categorywallpapers

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.categorywallpapers.paging.pagingcache.WallpapersCache
import ru.yotfr.categorywallpapers.paging.pagingcache.WallpapersCacheImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalPagingCacheDataModule {

    @Provides
    @Singleton
    internal fun provideWallpapersCache(): WallpapersCache {
        return WallpapersCacheImpl()
    }

}