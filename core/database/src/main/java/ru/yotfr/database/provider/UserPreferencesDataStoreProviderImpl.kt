package ru.yotfr.database.provider

import kotlinx.coroutines.flow.Flow
import ru.yotfr.database.datastore.UserPreferencesDataStore
import javax.inject.Inject

internal class UserPreferencesDataStoreProviderImpl @Inject constructor(
    private val userPreferencesDataStore: UserPreferencesDataStore
) : UserPreferencesDataStoreProvider {
    override suspend fun updateTheme(theme: String) {
        userPreferencesDataStore.updateTheme(theme = theme)
    }

    override fun getTheme(): Flow<String?> {
        return userPreferencesDataStore.getTheme()
    }

    override suspend fun updateAllowedOnlyWifi(value: Boolean) {
        userPreferencesDataStore.updateAllowedOnlyWifi(value = value)
    }

    override fun getAllowedOnlyWifi(): Flow<Boolean?> {
        return userPreferencesDataStore.getAllowedOnlyWifi()
    }

    override suspend fun getAllowedOnlyWifiValue(): Boolean? {
        return userPreferencesDataStore.getAllowedOnlyWifiValue()
    }
}