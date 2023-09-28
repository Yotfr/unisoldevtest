package ru.yotfr.wallpaperdownloads

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.wallpaperdownloads.wallpaperdownloader.WallpaperDownloader
import ru.yotfr.wallpaperdownloads.wallpaperdownloader.WallpaperDownloaderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalWallpaperDownloaderModule {

    @Binds
    @Singleton
    fun bindWallpaperDownloader(
        wallpaperDownloaderImpl: WallpaperDownloaderImpl
    ): WallpaperDownloader
}