package ru.yotfr.unisoldevtest.data.repository

import ru.yotfr.unisoldevtest.data.mapper.mapDomain
import ru.yotfr.unisoldevtest.data.mapper.mapEntity
import ru.yotfr.model.WallpaperDownload
import ru.yotfr.unisoldevtest.domain.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class WallpaperDownloadsRepositoryImpl @Inject constructor(
    private val downloadsDao: ru.yotfr.database.dao.WallpaperDownloadsDao
) : WallpaperDownloadsRepository {
    override suspend fun addNewDownload(download: ru.yotfr.model.WallpaperDownload) {
        downloadsDao.addNewDownload(download.mapEntity())
    }

    override suspend fun deleteDownload(download: ru.yotfr.model.WallpaperDownload) {
        downloadsDao.deleteDownload(download.mapEntity())
    }

    override suspend fun getDownloadByWallpaperId(wallpaperId: String): ru.yotfr.model.WallpaperDownload? {
        return downloadsDao.getDownloadByWallpaperId(wallpaperId)?.mapDomain()
    }

    override suspend fun getDownloadByDownloadId(downloadId: Long): ru.yotfr.model.WallpaperDownload? {
        return downloadsDao.getDownloadByDownloadId(downloadId)?.mapDomain()
    }
}