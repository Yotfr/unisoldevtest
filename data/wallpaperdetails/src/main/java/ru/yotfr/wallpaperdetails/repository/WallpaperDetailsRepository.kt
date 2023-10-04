package ru.yotfr.wallpaperdetails.repository

import kotlinx.coroutines.flow.Flow
import ru.yotfr.shared.model.ResponseResult
import ru.yotfr.shared.model.Wallpaper

interface WallpaperDetailsRepository {
    fun getWallpaperById(id: String): Flow<ResponseResult<Wallpaper>>
}