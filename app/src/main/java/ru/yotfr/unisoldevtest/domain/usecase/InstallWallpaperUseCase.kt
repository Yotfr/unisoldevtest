package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.wallpaperinstaller.WallpaperInstaller
import javax.inject.Inject

class InstallWallpaperUseCase @Inject constructor(
    private val wallpaperInstaller: ru.yotfr.wallpaperinstaller.WallpaperInstaller
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