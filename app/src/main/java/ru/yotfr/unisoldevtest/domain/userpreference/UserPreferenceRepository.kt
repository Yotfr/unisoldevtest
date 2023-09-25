package ru.yotfr.unisoldevtest.domain.userpreference

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.ThemeModel

interface UserPreference {
    suspend fun updateTheme(theme: ThemeModel)
    fun getTheme(): Flow<ThemeModel>
    suspend fun updateAllowedOnlyWifi(value: Boolean)
    fun getAllowedOnlyWifi(): Flow<Boolean>
    suspend fun getAllowedOnlyWifiValue(): Boolean
}