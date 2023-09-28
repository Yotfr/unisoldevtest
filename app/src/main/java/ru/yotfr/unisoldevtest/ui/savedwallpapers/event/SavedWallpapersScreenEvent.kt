package ru.yotfr.unisoldevtest.ui.savedwallpapers.event

import ru.yotfr.model.ErrorCause

sealed interface SavedWallpapersScreenEvent {
    data class ShowErrorToast(val error: ru.yotfr.model.ErrorCause) : SavedWallpapersScreenEvent
}