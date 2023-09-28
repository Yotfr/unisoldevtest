package ru.yotfr.unisoldevtest.ui.wallpaper.state

import ru.yotfr.model.Wallpaper

data class WallpaperScreenState(
    val isLoading: Boolean = false,
    val wallpaper: ru.yotfr.model.Wallpaper? = null,
    val isBarsVisible: Boolean = true
)