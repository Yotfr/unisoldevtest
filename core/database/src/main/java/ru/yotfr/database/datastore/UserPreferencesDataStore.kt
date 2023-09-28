package ru.yotfr.database.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

internal class UserPreferencesDataStore(
    private val dataStore: DataStore<Preferences>
)  {
    suspend fun updateTheme(theme: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME] = theme
        }
    }

    fun getTheme(): Flow<String?> {
        val theme = dataStore.data
            .map { preferences ->
                preferences[PreferencesKeys.THEME]
            }
        return theme
    }

    suspend fun updateAllowedOnlyWifi(value: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.ALLOWED_WIFI] = value
        }
    }

    fun getAllowedOnlyWifi(): Flow<Boolean?> {
        val allowedWifi = dataStore.data
            .map { preferences ->
                preferences[PreferencesKeys.ALLOWED_WIFI]
            }
        return allowedWifi
    }

    suspend fun getAllowedOnlyWifiValue(): Boolean? {
        return dataStore.data.firstOrNull()?.get(PreferencesKeys.ALLOWED_WIFI)
    }

    private object PreferencesKeys {
        val THEME = stringPreferencesKey("THEME")
        val ALLOWED_WIFI = booleanPreferencesKey("ALLOWED_WIFI")
    }
}