package ru.yotfr.settings

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalSettingsRepositoryModule::class])
@InstallIn(SingletonComponent::class)
object PublicSettingsRepositoryModule