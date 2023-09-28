package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.ResponseResult
import ru.yotfr.model.Wallpaper
import ru.yotfr.model.WallpaperInstallOption
import ru.yotfr.unisoldevtest.domain.wallpaperinstaller.WallpaperInstaller
import javax.inject.Inject

class InstallWallpaperUseCase @Inject constructor(
    private val wallpaperInstaller: WallpaperInstaller
) {
    operator fun invoke(
        wallpaper: ru.yotfr.model.Wallpaper,
        wallpaperInstallOption: ru.yotfr.model.WallpaperInstallOption
    ): Flow<ru.yotfr.model.ResponseResult<Unit>> {
        return wallpaperInstaller.installWallpaper(
            wallpaper = wallpaper,
            wallpaperInstallOption = wallpaperInstallOption
        )
    }
}