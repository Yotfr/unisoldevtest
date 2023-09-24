package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    suspend operator fun invoke(): Flow<ResponseResult<List<CategoryModel>>> = wallpaperRepository.getCategories()
}