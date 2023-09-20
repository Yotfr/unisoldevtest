package ru.yotfr.unisoldevtest.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.domain.model.Wallpaper

interface WallpaperRepository {
    fun getWallpapersByCategory(
        category: Category
    ): Flow<PagingData<Wallpaper>>

    suspend fun getCategories(): List<CategoryModel>

}