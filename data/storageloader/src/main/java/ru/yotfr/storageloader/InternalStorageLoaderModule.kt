package ru.yotfr.storageloader

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.storageloader.storageloader.StorageLoader
import ru.yotfr.storageloader.storageloader.StorageLoaderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalStorageLoaderModule {

    @Provides
    @Singleton
    fun provideStorageLoader(
        @ApplicationContext context: Context
    ): StorageLoader {
        return StorageLoaderImpl(context)
    }
}