package ru.yotfr.unisoldevtest.ui.favoritewallpapers.event

import ru.yotfr.model.Wallpaper

sealed interface FavoriteWallpapersEvent {
    data class ChangeFavorite(val wallpaper: ru.yotfr.model.Wallpaper) : FavoriteWallpapersEvent
}