package ru.yotfr.unisoldevtest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.downloader.DownloaderImpl
import ru.yotfr.unisoldevtest.domain.downloader.Downloader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DownloaderModule {

    @Provides
    @Singleton
    fun provideDownloader(
        @ApplicationContext context: Context
    ): Downloader {
        return DownloaderImpl(context)
    }
}