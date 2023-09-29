package ru.yotfr.settings


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.yotfr.database.provider.UserPreferencesDataStoreProvider
import ru.yotfr.model.ThemeModel
import javax.inject.Inject

internal class SettingsRepositoryImpl @Inject constructor(
    private val userPreferencesDataStoreProvider: UserPreferencesDataStoreProvider
) : SettingsRepository {
    override suspend fun updateTheme(theme: ThemeModel) {
        userPreferencesDataStoreProvider.updateTheme(
            theme = theme.name
        )
    }

    override fun getTheme(): Flow<ThemeModel> {
        return userPreferencesDataStoreProvider.getTheme().map { themeEnumValue ->
            themeEnumValue?.let { ThemeModel.valueOf(it) } ?: ThemeModel.SYSTEM_DEFAULT
        }
    }

    override suspend fun updateAllowedOnlyWifi(value: Boolean) {
        userPreferencesDataStoreProvider.updateAllowedOnlyWifi(value = value)
    }

    override fun getAllowedOnlyWifi(): Flow<Boolean> {
        return userPreferencesDataStoreProvider.getAllowedOnlyWifi().map {
            it ?: true
        }
    }

    override suspend fun getAllowedOnlyWifiValue(): Boolean {
        return userPreferencesDataStoreProvider.getAllowedOnlyWifiValue() ?: true
    }
}