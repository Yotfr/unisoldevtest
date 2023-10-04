package ru.yotfr.wallpaperinstaller.wallpaperinstaller

import kotlinx.coroutines.flow.Flow
import ru.yotfr.shared.model.ResponseResult
import ru.yotfr.shared.model.Wallpaper
import ru.yotfr.shared.model.WallpaperInstallOption

interface WallpaperInstaller {
    fun installWallpaper(
        wallpaper: Wallpaper, wallpaperInstallOption: WallpaperInstallOption
    ) : Flow<ResponseResult<Unit>>
}