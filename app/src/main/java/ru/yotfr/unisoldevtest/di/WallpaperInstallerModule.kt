package ru.yotfr.unisoldevtest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.wallpaperinstaller.WallpaperInstallerImpl
import ru.yotfr.unisoldevtest.domain.wallpaperinstaller.WallpaperInstaller
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WallpaperInstallerModule {

    @Provides
    @Singleton
    fun provideWallpaperInstaller(
        @ApplicationContext context: Context
    ): WallpaperInstaller {
        return WallpaperInstallerImpl(context)
    }

}