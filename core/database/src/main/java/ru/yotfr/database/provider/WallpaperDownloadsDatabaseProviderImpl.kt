package ru.yotfr.database.provider

import ru.yotfr.database.dao.WallpaperDownloadsDao
import ru.yotfr.database.entity.WallpaperDownloadsEntity
import javax.inject.Inject

internal class WallpaperDownloadsDatabaseProviderImpl @Inject constructor(
    private val wallpaperDownloadsDao: WallpaperDownloadsDao
) : WallpaperDownloadsDatabaseProvider {
    override suspend fun addNewDownload(download: WallpaperDownloadsEntity) {
        wallpaperDownloadsDao.addNewDownload(download = download)
    }

    override suspend fun deleteDownload(download: WallpaperDownloadsEntity) {
        wallpaperDownloadsDao.deleteDownload(download = download)
    }

    override suspend fun getDownloadByWallpaperId(wallpaperId: String): WallpaperDownloadsEntity? {
        return wallpaperDownloadsDao.getDownloadByWallpaperId(wallpaperId  = wallpaperId)
    }

    override suspend fun getDownloadByDownloadId(downloadId: Long): WallpaperDownloadsEntity? {
        return wallpaperDownloadsDao.getDownloadByDownloadId(downloadId = downloadId)
    }
}