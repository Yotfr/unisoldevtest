package ru.yotfr.unisoldevtest.ui.categories.event

import ru.yotfr.unisoldevtest.domain.model.ErrorCause

sealed interface CategoriesScreenEvent {
    data class ShowErrorToast(val error: ErrorCause) : CategoriesScreenEvent
}