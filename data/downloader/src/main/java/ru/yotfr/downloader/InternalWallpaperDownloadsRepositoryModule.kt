package ru.yotfr.downloader

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.downloader.repository.WallpaperDownloadsRepository
import ru.yotfr.downloader.repository.WallpaperDownloadsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalWallpaperDownloadsRepositoryModule {

    @Binds
    @Singleton
    fun bindWallpaperDownloadsRepository(
        wallpaperDownloadsRepositoryImpl: WallpaperDownloadsRepositoryImpl
    ): WallpaperDownloadsRepository
}