package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.wallpaperdownloads.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class DeleteWallpaperDownloadUseCase @Inject constructor(
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {
    suspend operator fun invoke(wallpaperDownload: ru.yotfr.model.WallpaperDownload) {
        wallpaperDownloadsRepository.deleteDownload(wallpaperDownload)
    }
}