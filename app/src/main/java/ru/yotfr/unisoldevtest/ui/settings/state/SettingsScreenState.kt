package ru.yotfr.unisoldevtest.ui.settings.state

import ru.yotfr.model.ThemeModel

data class SettingsScreenState(
    val currentTheme: ru.yotfr.model.ThemeModel = ru.yotfr.model.ThemeModel.SYSTEM_DEFAULT,
    val onlyWifiEnabled: Boolean = true
)
