package ru.yotfr.unisoldevtest.data.repository

import android.util.Log
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
        Log.d("TEST","ADD NEW DOWNLOAD")
        downloadsDao.addNewDownload(download.mapEntity())
    }

    override suspend fun deleteDownload(download: WallpaperDownload) {
        Log.d("TEST","DELETE DOWNLOAD")
        //downloadsDao.deleteDownload(download.mapEntity())
    }

    override suspend fun getDownloadByWallpaperId(wallpaperId: String): WallpaperDownload? {
        Log.d("TEST","GET DOWNLOAD BY WALL ID $wallpaperId")
        val downlWallpaper = downloadsDao.getDownloadByWallpaperId(wallpaperId)?.mapDomain()
        Log.d("TEST","GET DOWNLOAD BY WALL ID RESULT $downlWallpaper")
        return downlWallpaper
    }

    override suspend fun getDownloadByDownloadId(downloadId: Long): WallpaperDownload? {
        Log.d("TEST","GET DOWNLOAD BY D ID $downloadId")
        val downlWallpaper = downloadsDao.getDownloadByDownloadId(downloadId)?.mapDomain()
        Log.d("TEST","GET DOWNLOAD BY D ID RESULT $downlWallpaper")
        return downlWallpaper
    }
}