package ru.yotfr.unisoldevtest.ui.settings.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.yotfr.unisoldevtest.R
import ru.yotfr.unisoldevtest.domain.model.ThemeModel

@Composable
fun ThemeModel.displayName(): String {
    return when(this) {
        ThemeModel.LIGHT -> stringResource(id = R.string.light)
        ThemeModel.DARK -> stringResource(id = R.string.dark)
        ThemeModel.SYSTEM_DEFAULT -> stringResource(id = R.string.set_by_system)
    }
}