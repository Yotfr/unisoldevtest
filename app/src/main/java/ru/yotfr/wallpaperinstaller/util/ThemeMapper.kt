package ru.yotfr.wallpaperinstaller.util

import ru.yotfr.designsystem.theme.Theme
import ru.yotfr.shared.model.ThemeModel

fun ru.yotfr.shared.model.ThemeModel.map(): Theme {
    return when(this) {
        ru.yotfr.shared.model.ThemeModel.LIGHT -> Theme.LIGHT
        ru.yotfr.shared.model.ThemeModel.DARK -> Theme.DARK
        ru.yotfr.shared.model.ThemeModel.SYSTEM_DEFAULT -> Theme.SYSTEM_DEFAULT
    }
}