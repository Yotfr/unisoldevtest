package ru.yotfr.unisoldevtest.domain.userpreference

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.ThemeModel

interface UserPreference {
    suspend fun updateTheme(theme: ThemeModel)
    fun getTheme(): Flow<ThemeModel>
}