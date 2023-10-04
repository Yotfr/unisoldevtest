package ru.yotfr.wallpaperdownloads.usecase

import ru.yotfr.shared.model.WallpaperDownload
import ru.yotfr.wallpaperdownloads.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class GetDownloadByWallpaperIdUseCase @Inject constructor(
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {
    suspend operator fun invoke(wallpaperId: String): WallpaperDownload? {
        return wallpaperDownloadsRepository.getDownloadByWallpaperId(wallpaperId)
    }
}