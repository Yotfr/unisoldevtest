package ru.yotfr.wallpaperdetails.state

import ru.yotfr.shared.model.Wallpaper

internal data class WallpaperScreenState(
    val isLoading: Boolean = false,
    val wallpaper: Wallpaper? = null,
    val isBarsVisible: Boolean = true
)