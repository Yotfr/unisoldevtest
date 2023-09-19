package ru.yotfr.unisoldevtest.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.repository.WallpaperRepositoryImpl
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindWallpaperRepository(
        wallpaperRepositoryImpl: WallpaperRepositoryImpl
    ): WallpaperRepository
}