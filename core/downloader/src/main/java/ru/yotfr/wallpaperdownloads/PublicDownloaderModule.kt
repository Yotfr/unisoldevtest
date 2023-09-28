package ru.yotfr.wallpaperdownloads

import dagger.Module

@Module(includes = [InternalDownloaderModule::class])
object PublicDownloaderModule