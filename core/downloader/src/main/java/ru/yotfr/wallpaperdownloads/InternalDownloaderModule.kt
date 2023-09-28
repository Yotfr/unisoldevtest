package ru.yotfr.wallpaperdownloads

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.wallpaperdownloads.downloader.Downloader
import ru.yotfr.wallpaperdownloads.downloader.DownloaderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InternalDownloaderModule {

    @Provides
    @Singleton
    fun provideDownloader(
        @ApplicationContext context: Context
    ): Downloader {
        return DownloaderImpl(context)
    }
}