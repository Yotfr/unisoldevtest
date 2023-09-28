package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.wallpaperdownloads.wallpaperdownloader.WallpaperDownloader
import javax.inject.Inject

class CheckIfFileExistsUseCase @Inject constructor(
    private val wallpaperDownloader: WallpaperDownloader,
) {
    operator fun invoke(wallpaper: ru.yotfr.model.Wallpaper): Boolean {
        return wallpaperDownloader.checkIfWallpaperExists(wallpaper = wallpaper)
    }
}