package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.unisoldevtest.domain.downloader.Downloader
import javax.inject.Inject

class GetDownloadStatusUseCase @Inject constructor(
    private val downloader: Downloader
) {
    operator fun invoke(downloadId: Long): Int? {
        return downloader.getDownloadStatus(downloadId)
    }
}