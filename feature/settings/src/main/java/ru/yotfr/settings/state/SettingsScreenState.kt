package ru.yotfr.settings.state

import ru.yotfr.shared.model.ThemeModel

internal data class SettingsScreenState(
    val currentTheme: ThemeModel = ThemeModel.SYSTEM_DEFAULT,
    val onlyWifiEnabled: Boolean = true
)
