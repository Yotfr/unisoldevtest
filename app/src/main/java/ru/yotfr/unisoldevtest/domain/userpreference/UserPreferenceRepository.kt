package ru.yotfr.unisoldevtest.domain.userpreference

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.ThemeModel

interface UserPreference {
    suspend fun updateTheme(theme: ru.yotfr.model.ThemeModel)
    fun getTheme(): Flow<ru.yotfr.model.ThemeModel>
    suspend fun updateAllowedOnlyWifi(value: Boolean)
    fun getAllowedOnlyWifi(): Flow<Boolean>
    suspend fun getAllowedOnlyWifiValue(): Boolean
}