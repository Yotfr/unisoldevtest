package ru.yotfr.favoritewallpapers.repository

import kotlinx.coroutines.flow.Flow
import ru.yotfr.shared.model.Wallpaper

interface FavoriteWallpapersRepository {
    suspend fun changeWallpaperFavoriteStatus(wallpaper: Wallpaper)
    fun getFavoriteWallpapers(): Flow<List<Wallpaper>>
}