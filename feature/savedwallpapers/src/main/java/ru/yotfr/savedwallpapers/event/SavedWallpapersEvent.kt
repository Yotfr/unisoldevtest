package ru.yotfr.savedwallpapers.event

sealed interface SavedWallpapersEvent {
    data object PullRefresh : SavedWallpapersEvent
}