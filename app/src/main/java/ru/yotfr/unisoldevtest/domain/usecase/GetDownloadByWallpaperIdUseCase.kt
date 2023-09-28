package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.downloader.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class GetDownloadByWallpaperIdUseCase @Inject constructor(
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {
    suspend operator fun invoke(wallpaperId: String): ru.yotfr.model.WallpaperDownload? {
        return wallpaperDownloadsRepository.getDownloadByWallpaperId(wallpaperId)
    }
}