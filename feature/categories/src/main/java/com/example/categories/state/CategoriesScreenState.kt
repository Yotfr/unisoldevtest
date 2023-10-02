package com.example.categories.state

import ru.yotfr.model.CategoryModel
data class CategoriesScreenState(
    val isLoading: Boolean = false,
    val categories: List<CategoryModel> = emptyList()
)