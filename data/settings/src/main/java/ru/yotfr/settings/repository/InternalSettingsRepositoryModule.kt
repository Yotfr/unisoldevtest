package ru.yotfr.settings.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.settings.SettingsRepository
import ru.yotfr.settings.SettingsRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalSettingsRepositoryModule {

    @Binds
    @Singleton
    fun bindSettingsRepository(
        settingsRepositoryImpl: SettingsRepositoryImpl
    ): SettingsRepository
}