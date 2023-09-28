package ru.yotfr.unisoldevtest.domain.usecase

import android.app.DownloadManager
import ru.yotfr.unisoldevtest.domain.downloader.Downloader
import ru.yotfr.model.DownloadStatus
import javax.inject.Inject

class GetDownloadStatusUseCase @Inject constructor(
    private val downloader: Downloader
) {
    operator fun invoke(downloadId: Long): ru.yotfr.model.DownloadStatus {
        return when (downloader.getDownloadStatus(downloadId)) {
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