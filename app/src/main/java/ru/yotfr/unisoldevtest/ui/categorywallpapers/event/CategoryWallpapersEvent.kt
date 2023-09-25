package ru.yotfr.unisoldevtest.ui.categorywallpapers.event

import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.Wallpaper

sealed interface CategoryWallpapersEvent {
    data class ChangeFavorite(val wallpaper: Wallpaper) : CategoryWallpapersEvent
    data class SetCategory(val category: Category) : CategoryWallpapersEvent
    data object PullRefresh : CategoryWallpapersEvent
}