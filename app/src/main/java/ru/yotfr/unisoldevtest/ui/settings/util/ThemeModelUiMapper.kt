package ru.yotfr.unisoldevtest.ui.settings.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.yotfr.unisoldevtest.R
import ru.yotfr.model.ThemeModel

@Composable
fun ru.yotfr.model.ThemeModel.displayName(): String {
    return when(this) {
        ru.yotfr.model.ThemeModel.LIGHT -> stringResource(id = R.string.light)
        ru.yotfr.model.ThemeModel.DARK -> stringResource(id = R.string.dark)
        ru.yotfr.model.ThemeModel.SYSTEM_DEFAULT -> stringResource(id = R.string.set_by_system)
    }
}