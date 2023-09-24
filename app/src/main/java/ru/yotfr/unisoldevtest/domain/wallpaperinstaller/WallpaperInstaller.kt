package ru.yotfr.unisoldevtest.domain.wallpaperinstaller

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.model.WallpaperInstallOption

interface WallpaperInstaller {
    fun installWallpaper(
        wallpaper: Wallpaper, wallpaperInstallOption: WallpaperInstallOption
    ) : Flow<ResponseResult<Unit>>
}