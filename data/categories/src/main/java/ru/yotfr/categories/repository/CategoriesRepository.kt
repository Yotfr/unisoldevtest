package ru.yotfr.categories.repository

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.CategoryModel
import ru.yotfr.model.ResponseResult

interface CategoriesRepository {
    fun getCategories(): Flow<ResponseResult<List<CategoryModel>>>
}