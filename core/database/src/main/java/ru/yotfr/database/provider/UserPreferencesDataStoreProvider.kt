package ru.yotfr.database.provider

import kotlinx.coroutines.flow.Flow

interface UserPreferencesDataStoreProvider {
    suspend fun updateTheme(theme: String)

    fun getTheme(): Flow<String?>

    suspend fun updateAllowedOnlyWifi(value: Boolean)

    fun getAllowedOnlyWifi(): Flow<Boolean?>

    suspend fun getAllowedOnlyWifiValue(): Boolean?
}