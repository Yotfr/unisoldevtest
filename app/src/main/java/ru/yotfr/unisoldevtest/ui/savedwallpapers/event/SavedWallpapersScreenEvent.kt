package ru.yotfr.unisoldevtest.ui.savedwallpapers.event

import ru.yotfr.unisoldevtest.domain.model.ErrorCause

sealed interface SavedWallpapersScreenEvent {
    data class ShowErrorToast(val error: ErrorCause) : SavedWallpapersScreenEvent
}