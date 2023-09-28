package ru.yotfr.unisoldevtest.ui.wallpaper.event

import ru.yotfr.model.ErrorCause

sealed interface WallpaperScreenEvent {
    data object ShowDownloadCompleteSnackbar : WallpaperScreenEvent
    data object ShowDownloadInProgressSnackbar : WallpaperScreenEvent
    data object ShowDownloadFailedProgressSnackbar : WallpaperScreenEvent
    data object ShowFileAlreadySavedSnackbar : WallpaperScreenEvent
    data object ShowInstallInProgressSnackbar : WallpaperScreenEvent
    data object ShowInstallCompletedSnackbar : WallpaperScreenEvent
    data object ShowInstallFailedSnackbar : WallpaperScreenEvent
    data class ShowErrorSnackbar(val errorCause: ru.yotfr.model.ErrorCause) : WallpaperScreenEvent
    data object ShowDownloadOnlyByWifiAllowedSnackbar : WallpaperScreenEvent
}