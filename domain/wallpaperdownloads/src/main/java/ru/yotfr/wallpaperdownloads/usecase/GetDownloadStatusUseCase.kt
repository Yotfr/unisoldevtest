package ru.yotfr.wallpaperdownloads.usecase

import android.app.DownloadManager
import ru.yotfr.shared.model.DownloadStatus
import ru.yotfr.wallpaperdownloads.wallpaperdownloader.WallpaperDownloader
import javax.inject.Inject

class GetDownloadStatusUseCase @Inject constructor(
    private val wallpaperDownloader: WallpaperDownloader
) {
    operator fun invoke(downloadId: Long): DownloadStatus {
        return when (wallpaperDownloader.getWallpaperDownloadStatus(downloadId)) {
            null,
            DownloadManager.STATUS_FAILED -> {
                DownloadStatus.FAILED
            }

            DownloadManager.STATUS_PENDING,
            DownloadManager.STATUS_RUNNING,
            DownloadManager.STATUS_PAUSED -> {
                DownloadStatus.IN_PROGRESS
            }

            DownloadManager.STATUS_SUCCESSFUL -> {
                DownloadStatus.SUCCEED
            }

            else -> throw IllegalArgumentException("Wrong DownloadStatus")

        }
    }
}