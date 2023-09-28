package ru.yotfr.downloader

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalDownloaderModule::class])
@InstallIn(SingletonComponent::class)
object PublicDownloaderModule