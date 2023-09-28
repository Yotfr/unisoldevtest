package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.CategoryModel
import ru.yotfr.model.ResponseResult
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    suspend operator fun invoke(): Flow<ru.yotfr.model.ResponseResult<List<ru.yotfr.model.CategoryModel>>> =
        wallpaperRepository.getCategories()
}