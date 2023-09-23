package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.unisoldevtest.domain.model.WallpaperDownload
import ru.yotfr.unisoldevtest.domain.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class GetDownloadByWallpaperIdUseCase @Inject constructor(
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {
    suspend operator fun invoke(wallpaperId: String): WallpaperDownload? {
        return wallpaperDownloadsRepository.getDownloadByWallpaperId(wallpaperId)
    }
}