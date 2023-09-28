package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.ResponseResult
import ru.yotfr.model.Wallpaper
import javax.inject.Inject

class GetWallpaperByIdUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    operator fun invoke(id: String) : Flow<ru.yotfr.model.ResponseResult<ru.yotfr.model.Wallpaper>> {
        return wallpaperRepository.getWallpaperById(id)
    }
}