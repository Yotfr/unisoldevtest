package com.example.categories.state

import ru.yotfr.shared.model.CategoryModel
internal data class CategoriesScreenState(
    val isLoading: Boolean = false,
    val categories: List<CategoryModel> = emptyList()
)