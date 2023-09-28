package ru.yotfr.unisoldevtest.domain.repository

import ru.yotfr.model.WallpaperDownload

interface WallpaperDownloadsRepository {
    suspend fun addNewDownload(download: ru.yotfr.model.WallpaperDownload)

    suspend fun deleteDownload(download: ru.yotfr.model.WallpaperDownload)

    suspend fun getDownloadByWallpaperId(wallpaperId: String): ru.yotfr.model.WallpaperDownload?

    suspend fun getDownloadByDownloadId(downloadId: Long): ru.yotfr.model.WallpaperDownload?

}