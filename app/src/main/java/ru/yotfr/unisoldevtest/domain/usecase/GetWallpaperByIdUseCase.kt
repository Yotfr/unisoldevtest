package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetWallpaperByIdUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    operator fun invoke(id: String) : Flow<ResponseResult<Wallpaper>> {
        return wallpaperRepository.getWallpaperById(id)
    }
}