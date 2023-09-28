package ru.yotfr.unisoldevtest.ui.categorywallpapers.event

import ru.yotfr.model.Category
import ru.yotfr.model.Wallpaper

sealed interface CategoryWallpapersEvent {
    data class ChangeFavorite(val wallpaper: ru.yotfr.model.Wallpaper) : CategoryWallpapersEvent
    data class SetCategory(val category: ru.yotfr.model.Category) : CategoryWallpapersEvent
    data object PullRefresh : CategoryWallpapersEvent
}