package ru.yotfr.settings.state

import ru.yotfr.model.ThemeModel

data class SettingsScreenState(
    val currentTheme: ThemeModel = ThemeModel.SYSTEM_DEFAULT,
    val onlyWifiEnabled: Boolean = true
)
