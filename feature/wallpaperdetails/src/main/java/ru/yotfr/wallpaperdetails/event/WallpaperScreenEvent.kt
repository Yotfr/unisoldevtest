package ru.yotfr.wallpaperdetails.event

import ru.yotfr.shared.model.ErrorCause

internal sealed interface WallpaperScreenEvent {
    data object ShowDownloadCompleteSnackbar : WallpaperScreenEvent
    data object ShowDownloadInProgressSnackbar : WallpaperScreenEvent
    data object ShowDownloadFailedProgressSnackbar : WallpaperScreenEvent
    data object ShowFileAlreadySavedSnackbar : WallpaperScreenEvent
    data object ShowInstallInProgressSnackbar : WallpaperScreenEvent
    data object ShowInstallCompletedSnackbar : WallpaperScreenEvent
    data object ShowInstallFailedSnackbar : WallpaperScreenEvent
    data class ShowErrorSnackbar(val errorCause: ErrorCause) : WallpaperScreenEvent
    data object ShowDownloadOnlyByWifiAllowedSnackbar : WallpaperScreenEvent
}