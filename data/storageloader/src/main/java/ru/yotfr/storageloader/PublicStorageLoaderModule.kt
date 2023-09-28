package ru.yotfr.storageloader

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalStorageLoaderModule::class])
@InstallIn(SingletonComponent::class)
object PublicStorageLoaderModule