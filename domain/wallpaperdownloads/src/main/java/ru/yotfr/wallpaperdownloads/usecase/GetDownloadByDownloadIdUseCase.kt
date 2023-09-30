package ru.yotfr.wallpaperdownloads.usecase

import ru.yotfr.model.WallpaperDownload
import ru.yotfr.wallpaperdownloads.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class GetDownloadByDownloadIdUseCase @Inject constructor(
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {
    suspend operator fun invoke(downloadId: Long): WallpaperDownload? {
        return wallpaperDownloadsRepository.getDownloadByDownloadId(downloadId)
    }
}