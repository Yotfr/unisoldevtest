package ru.yotfr.wallpaperdetails

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.wallpaperdetails.repository.WallpaperDetailsRepository
import ru.yotfr.wallpaperdetails.repository.WallpaperDetailsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalWallpaperDetailsRepositoryModule {

    @Binds
    @Singleton
    fun bindWallpaperDetailsRepository(
        wallpaperDetailsRepositoryImpl: WallpaperDetailsRepositoryImpl
    ): WallpaperDetailsRepository
}