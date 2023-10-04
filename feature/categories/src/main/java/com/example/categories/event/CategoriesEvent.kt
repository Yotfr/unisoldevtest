package com.example.categories.event

internal sealed interface CategoriesEvent {
    data object PullRefresh : CategoriesEvent
}