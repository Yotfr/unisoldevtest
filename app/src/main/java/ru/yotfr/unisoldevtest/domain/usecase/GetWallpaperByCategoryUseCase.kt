package ru.yotfr.unisoldevtest.domain.usecase

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetWallpaperByCategoryUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    suspend operator fun invoke(
        category: Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<Wallpaper>> =
        wallpaperRepository.getWallpapersByCategory(category, coroutineScope)
}