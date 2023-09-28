package ru.yotfr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.yotfr.database.entity.WallpaperDownloadsEntity

@Dao
internal interface WallpaperDownloadsDao {

    @Insert(entity = WallpaperDownloadsEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewDownload(download: WallpaperDownloadsEntity)

    @Delete(entity = WallpaperDownloadsEntity::class)
    suspend fun deleteDownload(download: WallpaperDownloadsEntity)

    @Query("SELECT * FROM wallpaper_downloads WHERE wallpaperId = :wallpaperId")
    suspend fun getDownloadByWallpaperId(wallpaperId: String): WallpaperDownloadsEntity?

    @Query("SELECT * FROM wallpaper_downloads WHERE downloadId = :downloadId")
    suspend fun getDownloadByDownloadId(downloadId: Long): WallpaperDownloadsEntity?

}