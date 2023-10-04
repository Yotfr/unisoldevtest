package ru.yotfr.wallpaperdetails.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.yotfr.resources.R
import ru.yotfr.shared.model.WallpaperInstallOption

@Composable
internal fun WallpaperInstallOption.displayName(): String {
    return when(this) {
        WallpaperInstallOption.HOME_SCREEN -> stringResource(id = R.string.set_home)
        WallpaperInstallOption.LOCK_SCREEN -> stringResource(id = R.string.set_lock)
        WallpaperInstallOption.BOTH -> stringResource(id = R.string.set_both)
    }
}