package ru.yotfr.categories.repository

import kotlinx.coroutines.flow.Flow
import ru.yotfr.shared.model.CategoryModel
import ru.yotfr.shared.model.ResponseResult

interface CategoriesRepository {
    fun getCategories(): Flow<ResponseResult<List<CategoryModel>>>
}