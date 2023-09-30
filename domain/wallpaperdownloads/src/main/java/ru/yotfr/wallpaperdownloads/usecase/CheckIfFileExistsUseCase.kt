package ru.yotfr.wallpaperdownloads.usecase

import ru.yotfr.model.Wallpaper
import ru.yotfr.wallpaperdownloads.wallpaperdownloader.WallpaperDownloader
import javax.inject.Inject

class CheckIfFileExistsUseCase @Inject constructor(
    private val wallpaperDownloader: WallpaperDownloader,
) {
    operator fun invoke(wallpaper: Wallpaper): Boolean {
        return wallpaperDownloader.checkIfWallpaperExists(wallpaper = wallpaper)
    }
}