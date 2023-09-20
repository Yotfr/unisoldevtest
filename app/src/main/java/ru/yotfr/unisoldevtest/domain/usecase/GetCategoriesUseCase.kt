package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    suspend operator fun invoke(): List<CategoryModel> = wallpaperRepository.getCategories()
}