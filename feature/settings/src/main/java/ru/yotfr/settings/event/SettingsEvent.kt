package ru.yotfr.settings.event

import ru.yotfr.shared.model.ThemeModel

internal sealed interface SettingsEvent {
    data class ThemeChanged(val newTheme: ThemeModel) : SettingsEvent
    data class WiFiOnlyChanged(val newValue: Boolean) : SettingsEvent
}