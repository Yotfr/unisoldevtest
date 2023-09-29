package ru.yotfr.categorywallpapers.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.yotfr.categorywallpapers.repository.CategoryWallpapersRepository
import ru.yotfr.model.Category
import ru.yotfr.model.Wallpaper
import javax.inject.Inject

class GetWallpaperByCategoryUseCase @Inject constructor(
    private val categoryWallpapersRepository: CategoryWallpapersRepository
) {
    suspend operator fun invoke(
        category: Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<Wallpaper>> =
        categoryWallpapersRepository.getWallpapersByCategory(
            category = category,
            coroutineScope = coroutineScope
        )
}