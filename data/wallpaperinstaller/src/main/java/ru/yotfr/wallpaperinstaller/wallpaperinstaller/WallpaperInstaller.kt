package ru.yotfr.wallpaperinstaller.wallpaperinstaller

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.ResponseResult
import ru.yotfr.model.Wallpaper
import ru.yotfr.model.WallpaperInstallOption

interface WallpaperInstaller {
    fun installWallpaper(
        wallpaper: Wallpaper, wallpaperInstallOption: WallpaperInstallOption
    ) : Flow<ResponseResult<Unit>>
}