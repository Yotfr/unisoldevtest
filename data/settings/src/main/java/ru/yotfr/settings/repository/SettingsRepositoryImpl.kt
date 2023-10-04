package ru.yotfr.settings.repository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.yotfr.database.provider.UserPreferencesDataStoreProvider
import ru.yotfr.shared.model.ThemeModel
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val userPreferencesDataStoreProvider: UserPreferencesDataStoreProvider
) : SettingsRepository {
    override suspend fun updateTheme(theme: ThemeModel) {
        withContext(Dispatchers.IO) {
            userPreferencesDataStoreProvider.updateTheme(
                theme = theme.name
            )
        }
    }

    override fun getTheme(): Flow<ThemeModel> {
        return userPreferencesDataStoreProvider.getTheme().map { themeEnumValue ->
            themeEnumValue?.let { ThemeModel.valueOf(it) } ?: ThemeModel.SYSTEM_DEFAULT
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun updateAllowedOnlyWifi(value: Boolean) {
        withContext(Dispatchers.IO) {
            userPreferencesDataStoreProvider.updateAllowedOnlyWifi(value = value)
        }
    }

    override fun getAllowedOnlyWifi(): Flow<Boolean> {
        return userPreferencesDataStoreProvider.getAllowedOnlyWifi().map {
            it ?: true
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getAllowedOnlyWifiValue(): Boolean {
        return withContext(Dispatchers.IO) {
            userPreferencesDataStoreProvider.getAllowedOnlyWifiValue() ?: true
        }
    }
}