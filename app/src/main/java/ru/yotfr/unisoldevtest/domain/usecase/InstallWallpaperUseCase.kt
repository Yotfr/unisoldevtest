package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.model.WallpaperInstallOption
import ru.yotfr.unisoldevtest.domain.wallpaperinstaller.WallpaperInstaller
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