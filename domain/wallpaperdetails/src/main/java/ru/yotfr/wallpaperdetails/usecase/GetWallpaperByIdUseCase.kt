package ru.yotfr.wallpaperdetails.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.ResponseResult
import ru.yotfr.model.Wallpaper
import ru.yotfr.wallpaperdetails.repository.WallpaperDetailsRepository
import javax.inject.Inject

class GetWallpaperByIdUseCase @Inject constructor(
    private val wallpaperDetailsRepository: WallpaperDetailsRepository
) {
    operator fun invoke(id: String) : Flow<ResponseResult<Wallpaper>> {
        return wallpaperDetailsRepository.getWallpaperById(id)
    }
}