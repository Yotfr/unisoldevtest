package ru.yotfr.database

import dagger.Module
import javax.inject.Singleton

@Module(includes = [InternalDataStoreModule::class])
@Singleton
object PublicDataStoreModule