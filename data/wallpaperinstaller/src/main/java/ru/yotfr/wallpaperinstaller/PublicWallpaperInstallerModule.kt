package ru.yotfr.wallpaperinstaller

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalWallpaperInstallerModule::class])
@InstallIn(SingletonComponent::class)
object PublicWallpaperInstallerModule