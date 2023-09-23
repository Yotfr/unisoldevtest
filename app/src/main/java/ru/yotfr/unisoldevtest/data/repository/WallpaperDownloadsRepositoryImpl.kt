package ru.yotfr.unisoldevtest.data.repository

import ru.yotfr.unisoldevtest.data.datasource.local.dao.WallpaperDownloadsDao
import ru.yotfr.unisoldevtest.data.mapper.mapDomain
import ru.yotfr.unisoldevtest.data.mapper.mapEntity
import ru.yotfr.unisoldevtest.domain.model.WallpaperDownload
import ru.yotfr.unisoldevtest.domain.repository.WallpaperDownloadsRepository
import javax.inject.Inject

class WallpaperDownloadsRepositoryImpl @Inject constructor(
    private val downloadsDao: WallpaperDownloadsDao
) : WallpaperDownloadsRepository {
    override suspend fun addNewDownload(download: WallpaperDownload) {
        downloadsDao.addNewDownload(download.mapEntity())
    }

    override suspend fun deleteDownload(download: WallpaperDownload) {
        downloadsDao.deleteDownload(download.mapEntity())
    }

    override suspend fun getDownloadById(downloadId: Long): WallpaperDownload {
        return downloadsDao.getDownloadById(downloadId).mapDomain()
    }
}