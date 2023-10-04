package com.example.categories.event

import ru.yotfr.shared.model.ErrorCause

internal sealed interface CategoriesScreenEvent {
    data class ShowErrorToast(val error: ErrorCause) : CategoriesScreenEvent
}