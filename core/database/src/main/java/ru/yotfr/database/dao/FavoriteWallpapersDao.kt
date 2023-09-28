package ru.yotfr.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.yotfr.database.entity.WallpaperEntity

@Dao
internal interface FavoriteWallpapersDao {
    @Upsert(entity = WallpaperEntity::class)
    suspend fun upsertWallpaper(wallpaperEntity: WallpaperEntity)

    @Delete(entity = WallpaperEntity::class)
    suspend fun deleteWallpaper(wallpaperEntity: WallpaperEntity)

    @Query("SELECT * FROM wallpapers WHERE isFavorite = 1")
    fun getFavoriteWallpapers(): Flow<List<WallpaperEntity>>

    @Query("SELECT id FROM wallpapers WHERE isFavorite = 1")
    suspend fun getFavoriteWallpapersIds(): List<String>

    @Query("SELECT * FROM wallpapers WHERE id = :id")
    suspend fun getFavoriteWallpaperById(id: String): WallpaperEntity?


}