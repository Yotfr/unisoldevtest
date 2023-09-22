package ru.yotfr.unisoldevtest.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.data.datasource.local.entity.WallpaperEntity

@Dao
interface FavoriteWallpapersDao {
    @Upsert(entity = WallpaperEntity::class)
    suspend fun upsertWallpaper(wallpaperEntity: WallpaperEntity)

    @Delete(entity = WallpaperEntity::class)
    suspend fun deleteWallpaper(wallpaperEntity: WallpaperEntity)

    @Query("SELECT * FROM wallpapers WHERE isFavorite = 1")
    fun getFavoriteWallpapers(): Flow<List<WallpaperEntity>>

    @Query("SELECT id FROM wallpapers WHERE isFavorite = 1")
    suspend fun getFavoriteWallpapersIds(): List<String>


}