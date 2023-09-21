package ru.yotfr.unisoldevtest.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import ru.yotfr.unisoldevtest.data.datasource.local.entity.WallpaperEntity

@Dao
interface FavoriteWallpapersDao {
    @Insert(entity = WallpaperEntity::class)
    suspend fun insertWallpaper(wallpaperEntity: WallpaperEntity)

    @Delete(entity = WallpaperEntity::class)
    suspend fun deleteWallpaper(wallpaperEntity: WallpaperEntity)
}