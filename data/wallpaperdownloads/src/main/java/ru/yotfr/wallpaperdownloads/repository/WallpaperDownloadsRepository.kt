package ru.yotfr.wallpaperdownloads.repository

import ru.yotfr.shared.model.WallpaperDownload

interface WallpaperDownloadsRepository {
    suspend fun addNewDownload(download: WallpaperDownload)

    suspend fun deleteDownload(download: WallpaperDownload)

    suspend fun getDownloadByWallpaperId(wallpaperId: String): WallpaperDownload?

    suspend fun getDownloadByDownloadId(downloadId: Long): WallpaperDownload?

}