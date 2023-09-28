package ru.yotfr.unisoldevtest.ui.settings.event

import ru.yotfr.model.ThemeModel

sealed interface SettingsEvent {
    data class ThemeChanged(val newTheme: ru.yotfr.model.ThemeModel) : SettingsEvent
    data class WiFiOnlyChanged(val newValue: Boolean) : SettingsEvent
}