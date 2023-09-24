package ru.yotfr.unisoldevtest.data.userpreference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.yotfr.unisoldevtest.domain.model.ThemeModel
import ru.yotfr.unisoldevtest.domain.userpreference.UserPreference

class UserPreferenceImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreference {
    override suspend fun updateTheme(theme: ThemeModel) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.THEME] = theme.themeString
        }
    }

    override fun getTheme(): Flow<ThemeModel> {
        val theme = dataStore.data
            .map { preferences ->
                val prefThemeKey = preferences[PreferencesKeys.THEME]
                ThemeModel.values().firstOrNull { it.themeString == prefThemeKey }
                    ?: ThemeModel.SYSTEM_DEFAULT
            }
        return theme
    }

    private object PreferencesKeys {
        val THEME = stringPreferencesKey("THEME")
    }
}