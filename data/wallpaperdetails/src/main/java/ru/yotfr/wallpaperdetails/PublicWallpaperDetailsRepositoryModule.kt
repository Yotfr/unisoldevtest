package ru.yotfr.wallpaperdetails

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalWallpaperDetailsRepositoryModule::class])
@InstallIn(SingletonComponent::class)
object PublicWallpaperDetailsRepositoryModule