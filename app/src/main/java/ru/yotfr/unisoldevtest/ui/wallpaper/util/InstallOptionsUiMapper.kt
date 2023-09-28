package ru.yotfr.unisoldevtest.ui.wallpaper.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.yotfr.unisoldevtest.R
import ru.yotfr.model.WallpaperInstallOption

@Composable
fun ru.yotfr.model.WallpaperInstallOption.displayName(): String {
    return when(this) {
        ru.yotfr.model.WallpaperInstallOption.HOME_SCREEN -> stringResource(id = R.string.set_home)
        ru.yotfr.model.WallpaperInstallOption.LOCK_SCREEN -> stringResource(id = R.string.set_lock)
        ru.yotfr.model.WallpaperInstallOption.BOTH -> stringResource(id = R.string.set_both)
    }
}