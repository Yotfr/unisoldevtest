package ru.yotfr.unisoldevtest.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.Wallpaper

interface WallpaperRepository {
    fun getWallpapersFlow(): Flow<PagingData<Wallpaper>>
}