package ru.yotfr.unisoldevtest.ui.categories.event

sealed interface CategoriesEvent {
    data object PullRefresh : CategoriesEvent
}