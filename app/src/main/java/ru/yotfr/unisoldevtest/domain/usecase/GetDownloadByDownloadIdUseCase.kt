package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.downloader.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class GetDownloadByDownloadIdUseCase @Inject constructor(
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {
    suspend operator fun invoke(downloadId: Long): ru.yotfr.model.WallpaperDownload? {
        return wallpaperDownloadsRepository.getDownloadByDownloadId(downloadId)
    }
}