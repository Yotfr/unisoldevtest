package ru.yotfr.unisoldevtest.ui.savedwallpapers.event

sealed interface SavedWallpapersEvent {
    data object PullRefresh : SavedWallpapersEvent
}