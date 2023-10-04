package ru.yotfr.favoritewallpapers.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import ru.yotfr.database.provider.FavoriteWallpaperDatabaseProvider
import ru.yotfr.memorycache.pagingcache.WallpapersPagingCache
import ru.yotfr.shared.mapDomain
import ru.yotfr.shared.mapEntity
import ru.yotfr.shared.model.Wallpaper
import javax.inject.Inject

internal class FavoriteWallpapersRepositoryImpl @Inject constructor(
    private val favoriteWallpaperDatabaseProvider: FavoriteWallpaperDatabaseProvider,
    private val wallpapersPagingCache: WallpapersPagingCache
) : FavoriteWallpapersRepository {
    override suspend fun changeWallpaperFavoriteStatus(wallpaper: Wallpaper) {
        withContext(Dispatchers.IO) {
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
    }

    override fun getFavoriteWallpapers(): Flow<List<Wallpaper>> {
        return favoriteWallpaperDatabaseProvider.getFavoriteWallpapers()
            .map { it.map { it.mapDomain() } }.flowOn(Dispatchers.IO)
    }
}