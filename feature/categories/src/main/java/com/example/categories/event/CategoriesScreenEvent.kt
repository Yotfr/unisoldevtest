package com.example.categories.event

import ru.yotfr.model.ErrorCause

sealed interface CategoriesScreenEvent {
    data class ShowErrorToast(val error: ErrorCause) : CategoriesScreenEvent
}