package ru.yotfr.favoritewallpapers.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.yotfr.database.provider.FavoriteWallpaperDatabaseProvider
import ru.yotfr.memorycache.pagingcache.WallpapersPagingCache
import ru.yotfr.model.Wallpaper
import ru.yotfr.shared.mapDomain
import ru.yotfr.shared.mapEntity
import javax.inject.Inject

internal class FavoriteWallpapersRepositoryImpl @Inject constructor(
    private val favoriteWallpaperDatabaseProvider: FavoriteWallpaperDatabaseProvider,
    private val wallpapersPagingCache: WallpapersPagingCache
) : FavoriteWallpapersRepository {
    override suspend fun changeWallpaperFavoriteStatus(wallpaper: Wallpaper) {
        wallpapersPagingCache.updateWallpaperIsFavorite(
            wallpaperId = wallpaper.id,
            isFavorite = !wallpaper.isFavorite
        )
        val changedWallpaper = wallpaper.copy(isFavorite = true)
        if (wallpaper.isFavorite) {
            favoriteWallpaperDatabaseProvider.deleteWallpaper(wallpaper.mapEntity())
        } else {
            favoriteWallpaperDatabaseProvider.upsertWallpaper(changedWallpaper.mapEntity())
        }
    }

    override fun getFavoriteWallpapers(): Flow<List<Wallpaper>> {
        return favoriteWallpaperDatabaseProvider.getFavoriteWallpapers()
            .map { it.map { it.mapDomain() } }
    }
}