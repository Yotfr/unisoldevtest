package ru.yotfr.wallpaperdetails.state

import ru.yotfr.model.Wallpaper

data class WallpaperScreenState(
    val isLoading: Boolean = false,
    val wallpaper: Wallpaper? = null,
    val isBarsVisible: Boolean = true
)