package ru.yotfr.wallpaperdetails.repository

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.ResponseResult
import ru.yotfr.model.Wallpaper

interface WallpaperDetailsRepository {
    fun getWallpaperById(id: String): Flow<ResponseResult<Wallpaper>>
}