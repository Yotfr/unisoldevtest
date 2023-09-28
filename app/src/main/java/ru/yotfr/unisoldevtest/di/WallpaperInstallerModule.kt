package ru.yotfr.unisoldevtest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.wallpaperinstaller.WallpaperInstallerImpl
import ru.yotfr.wallpaperinstaller.WallpaperInstaller
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WallpaperInstallerModule {

    @Provides
    @Singleton
    fun provideWallpaperInstaller(
        @ApplicationContext context: Context
    ): ru.yotfr.wallpaperinstaller.WallpaperInstaller {
        return ru.yotfr.wallpaperinstaller.WallpaperInstallerImpl(context)
    }

}