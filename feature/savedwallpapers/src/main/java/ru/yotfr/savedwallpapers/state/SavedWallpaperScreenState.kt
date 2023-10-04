package ru.yotfr.savedwallpapers.state

import ru.yotfr.shared.model.DownloadedImages

internal data class SavedWallpaperScreenState(
    val isLoading: Boolean = false,
    val wallpapers: List<DownloadedImages> = emptyList()
)
