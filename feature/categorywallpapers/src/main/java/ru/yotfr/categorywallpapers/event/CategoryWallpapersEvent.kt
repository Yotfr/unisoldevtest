package ru.yotfr.categorywallpapers.event

import ru.yotfr.shared.model.Category
import ru.yotfr.shared.model.Wallpaper

internal sealed interface CategoryWallpapersEvent {
    data class ChangeFavorite(val wallpaper: Wallpaper) : CategoryWallpapersEvent
    data class SetCategory(val category: Category) : CategoryWallpapersEvent
    data object PullRefresh : CategoryWallpapersEvent
}