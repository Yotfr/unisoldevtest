package ru.yotfr.categorywallpapers.repository

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.yotfr.shared.model.Category
import ru.yotfr.shared.model.Wallpaper

interface CategoryWallpapersRepository {
    suspend fun getWallpapersByCategory(
        category: Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<Wallpaper>>
}