package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.unisoldevtest.domain.model.WallpaperDownload
import ru.yotfr.unisoldevtest.domain.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class GetDownloadByDownloadIdUseCase @Inject constructor(
    private val wallpaperDownloadsRepository: WallpaperDownloadsRepository
) {
    suspend operator fun invoke(downloadId: Long): WallpaperDownload? {
        return wallpaperDownloadsRepository.getDownloadByDownloadId(downloadId)
    }
}