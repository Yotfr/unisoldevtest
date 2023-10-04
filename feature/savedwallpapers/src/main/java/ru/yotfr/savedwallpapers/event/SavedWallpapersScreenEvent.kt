package ru.yotfr.savedwallpapers.event

import ru.yotfr.shared.model.ErrorCause

internal sealed interface SavedWallpapersScreenEvent {
    data class ShowErrorToast(val error: ErrorCause) : SavedWallpapersScreenEvent
}