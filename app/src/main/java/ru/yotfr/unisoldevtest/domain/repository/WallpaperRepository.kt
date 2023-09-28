package ru.yotfr.unisoldevtest.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface WallpaperRepository {
    suspend fun getWallpapersByCategory(
        category: ru.yotfr.model.Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<ru.yotfr.model.Wallpaper>>
    fun getWallpaperById(id: String): Flow<ru.yotfr.model.ResponseResult<ru.yotfr.model.Wallpaper>>
    suspend fun changeWallpaperFavoriteStatus(wallpaper: ru.yotfr.model.Wallpaper)
    fun getFavoriteWallpapers(): Flow<List<ru.yotfr.model.Wallpaper>>

}