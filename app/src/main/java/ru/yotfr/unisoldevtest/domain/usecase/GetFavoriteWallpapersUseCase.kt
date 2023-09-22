package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject

class GetFavoriteWallpapersUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    operator fun invoke(): Flow<List<Wallpaper>> =
        wallpaperRepository.getFavoriteWallpapers()
}