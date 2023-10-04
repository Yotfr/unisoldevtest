package ru.yotfr.settings.repository

import kotlinx.coroutines.flow.Flow
import ru.yotfr.shared.model.ThemeModel

interface SettingsRepository {
    suspend fun updateTheme(theme: ThemeModel)
    fun getTheme(): Flow<ThemeModel>
    suspend fun updateAllowedOnlyWifi(value: Boolean)
    fun getAllowedOnlyWifi(): Flow<Boolean>
    suspend fun getAllowedOnlyWifiValue(): Boolean
}