package ru.yotfr.unisoldevtest.ui.categories.state

import ru.yotfr.model.CategoryModel
data class CategoriesScreenState(
    val isLoading: Boolean = false,
    val categories: List<ru.yotfr.model.CategoryModel> = emptyList()
)