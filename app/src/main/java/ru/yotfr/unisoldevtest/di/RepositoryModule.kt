package ru.yotfr.unisoldevtest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.repository.WallpaperDownloadsRepositoryImpl
import ru.yotfr.unisoldevtest.data.repository.WallpaperRepositoryImpl
import ru.yotfr.unisoldevtest.domain.repository.WallpaperDownloadsRepository
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindWallpaperRepository(
        wallpaperRepositoryImpl: WallpaperRepositoryImpl
    ): WallpaperRepository

    @Binds
    @Singleton
    fun bindWallpaperDownloadsRepository(
        wallpaperDownloadsRepositoryImpl: WallpaperDownloadsRepositoryImpl
    ): WallpaperDownloadsRepository
}