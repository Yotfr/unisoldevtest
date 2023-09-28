package ru.yotfr.database.provider

import kotlinx.coroutines.flow.Flow
import ru.yotfr.database.entity.WallpaperEntity

interface FavoriteWallpaperDatabaseProvider {
    suspend fun upsertWallpaper(wallpaperEntity: WallpaperEntity)

    suspend fun deleteWallpaper(wallpaperEntity: WallpaperEntity)

    fun getFavoriteWallpapers(): Flow<List<WallpaperEntity>>

    suspend fun getFavoriteWallpapersIds(): List<String>
}