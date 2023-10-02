package ru.yotfr.categorywallpapers.event

import ru.yotfr.model.Category
import ru.yotfr.model.Wallpaper

sealed interface CategoryWallpapersEvent {
    data class ChangeFavorite(val wallpaper: Wallpaper) : CategoryWallpapersEvent
    data class SetCategory(val category: Category) : CategoryWallpapersEvent
    data object PullRefresh : CategoryWallpapersEvent
}