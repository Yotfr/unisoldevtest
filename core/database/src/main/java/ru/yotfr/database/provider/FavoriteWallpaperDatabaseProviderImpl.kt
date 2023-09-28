package ru.yotfr.database.provider

import kotlinx.coroutines.flow.Flow
import ru.yotfr.database.dao.FavoriteWallpapersDao
import ru.yotfr.database.entity.WallpaperEntity
import javax.inject.Inject

internal class FavoriteWallpaperDatabaseProviderImpl @Inject constructor(
    private val favoriteWallpapersDao: FavoriteWallpapersDao
) : FavoriteWallpaperDatabaseProvider {
    override suspend fun upsertWallpaper(wallpaperEntity: WallpaperEntity) {
        favoriteWallpapersDao.upsertWallpaper(wallpaperEntity = wallpaperEntity)
    }

    override suspend fun deleteWallpaper(wallpaperEntity: WallpaperEntity) {
        favoriteWallpapersDao.deleteWallpaper(wallpaperEntity = wallpaperEntity)
    }

    override fun getFavoriteWallpapers(): Flow<List<WallpaperEntity>> {
        return favoriteWallpapersDao.getFavoriteWallpapers()
    }

    override suspend fun getFavoriteWallpapersIds(): List<String> {
        return favoriteWallpapersDao.getFavoriteWallpapersIds()
    }
}