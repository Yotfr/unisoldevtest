package ru.yotfr.wallpaperdownloads.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.database.provider.WallpaperDownloadsDatabaseProvider
import ru.yotfr.shared.mapDomain
import ru.yotfr.shared.mapEntity
import ru.yotfr.shared.model.WallpaperDownload
import javax.inject.Inject

internal class WallpaperDownloadsRepositoryImpl @Inject constructor(
    private val wallpaperDownloadsDatabaseProvider: WallpaperDownloadsDatabaseProvider
) : WallpaperDownloadsRepository {
    override suspend fun addNewDownload(download: WallpaperDownload) {
        withContext(Dispatchers.IO) {
            wallpaperDownloadsDatabaseProvider.addNewDownload(download.mapEntity())
        }
    }

    override suspend fun deleteDownload(download: WallpaperDownload) {
        withContext(Dispatchers.IO) {
            wallpaperDownloadsDatabaseProvider.deleteDownload(download.mapEntity())
        }
    }

    override suspend fun getDownloadByWallpaperId(wallpaperId: String): WallpaperDownload? {
        return withContext(Dispatchers.IO) {
            wallpaperDownloadsDatabaseProvider.getDownloadByWallpaperId(wallpaperId)?.mapDomain()
        }
    }

    override suspend fun getDownloadByDownloadId(downloadId: Long): WallpaperDownload? {
        return withContext(Dispatchers.IO) {
            wallpaperDownloadsDatabaseProvider.getDownloadByDownloadId(downloadId)?.mapDomain()
        }
    }
}