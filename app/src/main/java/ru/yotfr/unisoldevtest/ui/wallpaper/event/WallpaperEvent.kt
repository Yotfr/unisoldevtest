package ru.yotfr.unisoldevtest.ui.wallpaper.event
sealed interface WallpaperEvent {
    data class EnteredScreen(val wallpaperId: String) : WallpaperEvent
    data object ChangeBarsVisibility : WallpaperEvent
    data object DownloadWallpaper : WallpaperEvent
    data class DownloadCompleted(val downloadId: Long) : WallpaperEvent
}