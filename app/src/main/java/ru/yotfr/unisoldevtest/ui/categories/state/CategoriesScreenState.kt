package ru.yotfr.unisoldevtest.ui.categories.state

import ru.yotfr.unisoldevtest.domain.model.CategoryModel
data class CategoriesScreenState(
    val isLoading: Boolean = false,
    val categories: List<CategoryModel> = emptyList()
)