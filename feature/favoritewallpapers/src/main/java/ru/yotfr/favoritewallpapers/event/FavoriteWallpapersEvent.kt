package ru.yotfr.favoritewallpapers.event

import ru.yotfr.model.Wallpaper

sealed interface FavoriteWallpapersEvent {
    data class ChangeFavorite(val wallpaper: Wallpaper) : FavoriteWallpapersEvent
}