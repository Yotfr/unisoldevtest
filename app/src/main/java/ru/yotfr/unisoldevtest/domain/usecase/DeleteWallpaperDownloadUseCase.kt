package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.model.WallpaperDownload
import ru.yotfr.unisoldevtest.domain.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class DeleteWallpaperDownloadUseCase @Inject constructor(
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {
    suspend operator fun invoke(wallpaperDownload: ru.yotfr.model.WallpaperDownload) {
        wallpaperDownloadsRepository.deleteDownload(wallpaperDownload)
    }
}