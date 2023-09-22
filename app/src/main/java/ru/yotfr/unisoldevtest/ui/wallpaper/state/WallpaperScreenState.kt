package ru.yotfr.unisoldevtest.ui.wallpaper.state

import ru.yotfr.unisoldevtest.domain.model.Wallpaper

data class WallpaperScreenState(
    val isLoading: Boolean = false,
    val wallpaper: Wallpaper? = null,
    val isBarsVisible: Boolean = true
)