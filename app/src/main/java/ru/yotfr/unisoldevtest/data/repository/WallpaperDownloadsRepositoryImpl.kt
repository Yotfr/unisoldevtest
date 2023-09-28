package ru.yotfr.unisoldevtest.data.repository

import ru.yotfr.database.dao.WallpaperDownloadsDao
import ru.yotfr.unisoldevtest.data.mapper.mapDomain
import ru.yotfr.unisoldevtest.data.mapper.mapEntity
import ru.yotfr.unisoldevtest.domain.model.WallpaperDownload
import ru.yotfr.unisoldevtest.domain.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class WallpaperDownloadsRepositoryImpl @Inject constructor(
    private val downloadsDao: ru.yotfr.database.dao.WallpaperDownloadsDao
) : WallpaperDownloadsRepository {
    override suspend fun addNewDownload(download: WallpaperDownload) {
        downloadsDao.addNewDownload(download.mapEntity())
    }

    override suspend fun deleteDownload(download: WallpaperDownload) {
        downloadsDao.deleteDownload(download.mapEntity())
    }

    override suspend fun getDownloadByWallpaperId(wallpaperId: String): WallpaperDownload? {
        return downloadsDao.getDownloadByWallpaperId(wallpaperId)?.mapDomain()
    }

    override suspend fun getDownloadByDownloadId(downloadId: Long): WallpaperDownload? {
        return downloadsDao.getDownloadByDownloadId(downloadId)?.mapDomain()
    }
}