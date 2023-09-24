package ru.yotfr.unisoldevtest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.storageloader.StorageLoaderImpl
import ru.yotfr.unisoldevtest.domain.storageloader.StorageLoader
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageLoaderModule {

    @Provides
    @Singleton
    fun provideStorageLoader(
        @ApplicationContext context: Context
    ): StorageLoader {
        return StorageLoaderImpl(context)
    }
}