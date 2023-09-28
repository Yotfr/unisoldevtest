package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject

class ChangeWallpaperFavoriteStatusUseCase @Inject constructor(
    private val wallpaperRepository: WallpaperRepository
) {
    suspend operator fun invoke(wallpaper: ru.yotfr.model.Wallpaper) {
        wallpaperRepository.changeWallpaperFavoriteStatus(wallpaper)
    }
}