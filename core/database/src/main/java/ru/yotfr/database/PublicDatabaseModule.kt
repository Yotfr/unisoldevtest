package ru.yotfr.database

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalDatabaseModule::class])
@InstallIn(SingletonComponent::class)
object PublicDatabaseModule