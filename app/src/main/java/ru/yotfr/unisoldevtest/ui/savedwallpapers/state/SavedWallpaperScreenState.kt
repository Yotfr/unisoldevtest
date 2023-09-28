package ru.yotfr.unisoldevtest.ui.savedwallpapers.state

import ru.yotfr.model.DownloadedImages

data class SavedWallpaperScreenState(
    val isLoading: Boolean = false,
    val wallpapers: List<ru.yotfr.model.DownloadedImages> = emptyList()
)
