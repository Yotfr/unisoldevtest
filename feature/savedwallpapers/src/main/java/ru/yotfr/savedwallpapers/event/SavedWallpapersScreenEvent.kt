package ru.yotfr.savedwallpapers.event

import ru.yotfr.model.ErrorCause

sealed interface SavedWallpapersScreenEvent {
    data class ShowErrorToast(val error: ErrorCause) : SavedWallpapersScreenEvent
}