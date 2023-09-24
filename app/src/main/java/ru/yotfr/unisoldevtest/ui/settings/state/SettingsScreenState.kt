package ru.yotfr.unisoldevtest.ui.settings.state

import ru.yotfr.unisoldevtest.domain.model.ThemeModel

data class SettingsScreenState(
    val currentTheme: ThemeModel = ThemeModel.SYSTEM_DEFAULT
)
