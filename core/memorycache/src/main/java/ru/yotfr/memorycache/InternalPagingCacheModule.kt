package ru.yotfr.memorycache

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.memorycache.pagingcache.WallpapersPagingCache
import ru.yotfr.memorycache.pagingcache.WallpapersPagingCacheImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalPagingCacheModule {
    @Binds
    @Singleton
    fun bindWallpapersPagingCache(
        wallpapersPagingCacheImpl: WallpapersPagingCacheImpl
    ): WallpapersPagingCache
}