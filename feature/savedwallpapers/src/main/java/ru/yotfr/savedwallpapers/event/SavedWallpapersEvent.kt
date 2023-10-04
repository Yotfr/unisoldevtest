package ru.yotfr.savedwallpapers.event

internal sealed interface SavedWallpapersEvent {
    data object PullRefresh : SavedWallpapersEvent
}