package ru.yotfr.unisoldevtest.ui.favoritewallpapers.event

import ru.yotfr.unisoldevtest.domain.model.Wallpaper

sealed interface FavoriteWallpapersEvent {
    data class ChangeFavorite(val wallpaper: Wallpaper) : FavoriteWallpapersEvent
}