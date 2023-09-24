package ru.yotfr.unisoldevtest.ui.settings.event

import ru.yotfr.unisoldevtest.domain.model.ThemeModel

sealed interface SettingsEvent {
    data class ThemeChanged(val newTheme: ThemeModel) : SettingsEvent
}