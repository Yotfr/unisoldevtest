package ru.yotfr.unisoldevtest.ui.savedwallpapers.state

import ru.yotfr.unisoldevtest.domain.model.DownloadedImages

data class SavedWallpaperScreenState(
    val isLoading: Boolean = false,
    val wallpapers: List<DownloadedImages> = emptyList()
)
