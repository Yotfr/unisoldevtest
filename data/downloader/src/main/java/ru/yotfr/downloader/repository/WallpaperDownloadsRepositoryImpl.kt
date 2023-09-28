package ru.yotfr.downloader.repository

import ru.yotfr.database.provider.WallpaperDownloadsDatabaseProvider
import ru.yotfr.shared.mapDomain
import ru.yotfr.shared.mapEntity
import javax.inject.Inject

internal class WallpaperDownloadsRepositoryImpl @Inject constructor(
    private val wallpaperDownloadsDatabaseProvider: WallpaperDownloadsDatabaseProvider
) : WallpaperDownloadsRepository {
    override suspend fun addNewDownload(download: ru.yotfr.model.WallpaperDownload) {
        wallpaperDownloadsDatabaseProvider.addNewDownload(download.mapEntity())
    }

    override suspend fun deleteDownload(download: ru.yotfr.model.WallpaperDownload) {
        wallpaperDownloadsDatabaseProvider.deleteDownload(download.mapEntity())
    }

    override suspend fun getDownloadByWallpaperId(wallpaperId: String): ru.yotfr.model.WallpaperDownload? {
        return wallpaperDownloadsDatabaseProvider.getDownloadByWallpaperId(wallpaperId)?.mapDomain()
    }

    override suspend fun getDownloadByDownloadId(downloadId: Long): ru.yotfr.model.WallpaperDownload? {
        return wallpaperDownloadsDatabaseProvider.getDownloadByDownloadId(downloadId)?.mapDomain()
    }
}