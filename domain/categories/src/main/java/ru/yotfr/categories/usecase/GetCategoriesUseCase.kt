package ru.yotfr.categories.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.categories.repository.CategoriesRepository
import ru.yotfr.model.CategoryModel
import ru.yotfr.model.ResponseResult
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepository
) {
    suspend operator fun invoke(): Flow<ResponseResult<List<CategoryModel>>> {
        return categoriesRepository.getCategories()
    }
}