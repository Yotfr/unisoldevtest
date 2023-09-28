package ru.yotfr.unisoldevtest.ui.categories.event

import ru.yotfr.model.ErrorCause

sealed interface CategoriesScreenEvent {
    data class ShowErrorToast(val error: ru.yotfr.model.ErrorCause) : CategoriesScreenEvent
}