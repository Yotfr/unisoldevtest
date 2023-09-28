package ru.yotfr.downloader

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.downloader.downloader.Downloader
import ru.yotfr.downloader.downloader.DownloaderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalDownloaderModule {

    @Provides
    @Singleton
    fun provideDownloader(
        @ApplicationContext context: Context
    ): Downloader {
        return DownloaderImpl(context)
    }
}