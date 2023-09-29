package ru.yotfr.wallpaperinstaller

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.wallpaperinstaller.wallpaperinstaller.WallpaperInstaller
import ru.yotfr.wallpaperinstaller.wallpaperinstaller.WallpaperInstallerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalWallpaperInstallerModule {

    @Provides
    @Singleton
    fun bindWallpaperInstaller(
       wallpaperInstallerImpl: WallpaperInstallerImpl
    ): WallpaperInstaller
}