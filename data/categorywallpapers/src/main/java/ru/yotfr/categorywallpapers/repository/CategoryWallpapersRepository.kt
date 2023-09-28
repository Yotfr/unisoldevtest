package ru.yotfr.categorywallpapers.repository

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.Wallpaper

interface CategoryWallpapersRepository {
    suspend fun getWallpapersByCategory(
        category: ru.yotfr.model.Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<Wallpaper>>
}