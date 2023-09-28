package ru.yotfr.unisoldevtest.domain.usecase

import android.app.DownloadManager
import ru.yotfr.wallpaperdownloads.wallpaperdownloader.WallpaperDownloader
import javax.inject.Inject

class GetDownloadStatusUseCase @Inject constructor(
    private val wallpaperDownloader: WallpaperDownloader
) {
    operator fun invoke(downloadId: Long): ru.yotfr.model.DownloadStatus {
        return when (wallpaperDownloader.getWallpaperDownloadStatus(downloadId)) {
            null,
            DownloadManager.STATUS_FAILED -> {
                ru.yotfr.model.DownloadStatus.FAILED
            }

            DownloadManager.STATUS_PENDING,
            DownloadManager.STATUS_RUNNING,
            DownloadManager.STATUS_PAUSED -> {
                ru.yotfr.model.DownloadStatus.IN_PROGRESS
            }

            DownloadManager.STATUS_SUCCESSFUL -> {
                ru.yotfr.model.DownloadStatus.SUCCEED
            }

            else -> throw IllegalArgumentException("Wrong DownloadStatus")

        }
    }
}