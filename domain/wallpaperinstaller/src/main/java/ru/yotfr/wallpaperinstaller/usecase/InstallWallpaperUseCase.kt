package ru.yotfr.wallpaperinstaller.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.shared.model.ResponseResult
import ru.yotfr.shared.model.Wallpaper
import ru.yotfr.shared.model.WallpaperInstallOption
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