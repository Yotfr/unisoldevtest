package ru.yotfr.wallpaperdownloads

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalWallpaperDownloaderModule::class])
@InstallIn(SingletonComponent::class)
object PublicWallpaperDownloaderModule