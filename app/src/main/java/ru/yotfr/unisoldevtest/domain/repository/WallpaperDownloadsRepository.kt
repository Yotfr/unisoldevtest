package ru.yotfr.unisoldevtest.domain.repository

import ru.yotfr.unisoldevtest.domain.model.WallpaperDownload

interface WallpaperDownloadsRepository {
    suspend fun addNewDownload(download: WallpaperDownload)

    suspend fun deleteDownload(download: WallpaperDownload)

    suspend fun getDownloadByWallpaperId(wallpaperId: String): WallpaperDownload?

    suspend fun getDownloadByDownloadId(downloadId: Long): WallpaperDownload?

}