package ru.yotfr.unisoldevtest.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.model.Wallpaper

interface WallpaperRepository {
    suspend fun getWallpapersByCategory(
        category: Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<Wallpaper>>

    fun getCategories(): Flow<ResponseResult<List<CategoryModel>>>
    fun getWallpaperById(id: String): Flow<ResponseResult<Wallpaper>>
    suspend fun changeWallpaperFavoriteStatus(wallpaper: Wallpaper)
    fun getFavoriteWallpapers(): Flow<List<Wallpaper>>

}