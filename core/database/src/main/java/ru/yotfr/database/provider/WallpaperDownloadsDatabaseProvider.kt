package ru.yotfr.database.provider

import ru.yotfr.database.entity.WallpaperDownloadsEntity

interface WallpaperDownloadsDatabaseProvider {
    suspend fun addNewDownload(download: WallpaperDownloadsEntity)

    suspend fun deleteDownload(download: WallpaperDownloadsEntity)

    suspend fun getDownloadByWallpaperId(wallpaperId: String): WallpaperDownloadsEntity?

    suspend fun getDownloadByDownloadId(downloadId: Long): WallpaperDownloadsEntity?
}