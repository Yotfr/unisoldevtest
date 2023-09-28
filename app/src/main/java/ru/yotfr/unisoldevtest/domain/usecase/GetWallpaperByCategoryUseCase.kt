package ru.yotfr.unisoldevtest.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.Category
import ru.yotfr.model.Wallpaper
import javax.inject.Inject

class GetWallpaperByCategoryUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    suspend operator fun invoke(
        category: ru.yotfr.model.Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<ru.yotfr.model.Wallpaper>> =
        wallpaperRepository.getWallpapersByCategory(category, coroutineScope)
}