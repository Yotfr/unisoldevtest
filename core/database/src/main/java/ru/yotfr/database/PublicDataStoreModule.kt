package ru.yotfr.database

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [InternalDataStoreModule::class])
@InstallIn(SingletonComponent::class)
object PublicDataStoreModule