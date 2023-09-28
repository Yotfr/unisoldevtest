package ru.yotfr.unisoldevtest.ui.wallpaper.event

import ru.yotfr.model.WallpaperInstallOption

sealed interface WallpaperEvent {
    data class EnteredScreen(val wallpaperId: String) : WallpaperEvent
    data object ChangeBarsVisibility : WallpaperEvent
    data object DownloadWallpaper : WallpaperEvent
    data class DownloadCompleted(val downloadId: Long) : WallpaperEvent
    data class InstallWallpaper(val wallpaperInstallOption: ru.yotfr.model.WallpaperInstallOption) : WallpaperEvent
    data object ChangeFavoriteStatus : WallpaperEvent
}