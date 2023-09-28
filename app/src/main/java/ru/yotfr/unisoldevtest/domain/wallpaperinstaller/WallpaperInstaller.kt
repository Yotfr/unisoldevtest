package ru.yotfr.unisoldevtest.domain.wallpaperinstaller

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.ResponseResult
import ru.yotfr.model.Wallpaper
import ru.yotfr.model.WallpaperInstallOption

interface WallpaperInstaller {
    fun installWallpaper(
        wallpaper: ru.yotfr.model.Wallpaper, wallpaperInstallOption: ru.yotfr.model.WallpaperInstallOption
    ) : Flow<ru.yotfr.model.ResponseResult<Unit>>
}