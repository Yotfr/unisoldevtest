package ru.yotfr.wallpaperdownloads.usecase

import ru.yotfr.model.WallpaperDownload
import ru.yotfr.wallpaperdownloads.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class DeleteWallpaperDownloadUseCase @Inject constructor(
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {
    suspend operator fun invoke(wallpaperDownload: WallpaperDownload) {
        wallpaperDownloadsRepository.deleteDownload(wallpaperDownload)
    }
}