package com.example.categories.event

sealed interface CategoriesEvent {
    data object PullRefresh : CategoriesEvent
}