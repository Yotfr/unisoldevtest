package ru.yotfr.wallpaperinstaller

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.wallpaperinstaller.wallpaperinstaller.WallpaperInstaller
import ru.yotfr.wallpaperinstaller.wallpaperinstaller.WallpaperInstallerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalWallpaperInstallerModule {

    @Provides
    @Singleton
    fun provideWallpaperInstaller(
        @ApplicationContext context: Context
    ): WallpaperInstaller {
        return WallpaperInstallerImpl(context)
    }
}