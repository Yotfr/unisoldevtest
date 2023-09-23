package ru.yotfr.unisoldevtest.ui.wallpaper.event

sealed interface WallpaperScreenEvent {
    data object ShowDownloadCompleteSnackbar : WallpaperScreenEvent
    data object ShowDownloadInProgressSnackbar : WallpaperScreenEvent
    data object ShowDownloadFailedProgressSnackbar : WallpaperScreenEvent
    data object ShowFileAlreadySavedSnackbar : WallpaperScreenEvent
}