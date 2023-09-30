package ru.yotfr.wallpaperinstaller.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.ResponseResult
import ru.yotfr.model.Wallpaper
import ru.yotfr.model.WallpaperInstallOption
import ru.yotfr.wallpaperinstaller.wallpaperinstaller.WallpaperInstaller
import javax.inject.Inject

class InstallWallpaperUseCase @Inject constructor(
    private val wallpaperInstaller: WallpaperInstaller
) {
    operator fun invoke(
        wallpaper: Wallpaper,
        wallpaperInstallOption: WallpaperInstallOption
    ): Flow<ResponseResult<Unit>> {
        return wallpaperInstaller.installWallpaper(
            wallpaper = wallpaper,
            wallpaperInstallOption = wallpaperInstallOption
        )
    }
}