package ru.yotfr.favoritewallpapers.event

import ru.yotfr.shared.model.Wallpaper

internal sealed interface FavoriteWallpapersEvent {
    data class ChangeFavorite(val wallpaper: Wallpaper) : FavoriteWallpapersEvent
}