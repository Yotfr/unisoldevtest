package ru.yotfr.unisoldevtest.domain.usecase

import android.app.DownloadManager
import ru.yotfr.unisoldevtest.domain.downloader.Downloader
import ru.yotfr.unisoldevtest.domain.model.DownloadStatus
import javax.inject.Inject

class GetDownloadStatusUseCase @Inject constructor(
    private val downloader: Downloader
) {
    operator fun invoke(downloadId: Long): DownloadStatus {
        return when (downloader.getDownloadStatus(downloadId)) {
            null,
            DownloadManager.STATUS_FAILED -> DownloadStatus.FAILED

            DownloadManager.STATUS_PENDING,
            DownloadManager.STATUS_RUNNING,
            DownloadManager.STATUS_PAUSED -> DownloadStatus.IN_PROGRESS

            DownloadManager.STATUS_SUCCESSFUL -> DownloadStatus.SUCCEED

            else -> throw IllegalArgumentException("Wrong DownloadStatus")

        }
    }
}