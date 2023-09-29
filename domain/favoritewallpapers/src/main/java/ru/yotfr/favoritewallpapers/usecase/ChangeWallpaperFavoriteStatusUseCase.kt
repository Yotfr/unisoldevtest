package ru.yotfr.favoritewallpapers.usecase

import ru.yotfr.favoritewallpapers.repository.FavoriteWallpapersRepository
import ru.yotfr.model.Wallpaper
import javax.inject.Inject

class ChangeWallpaperFavoriteStatusUseCase @Inject constructor(
    private val favoriteWallpapersRepository: FavoriteWallpapersRepository
) {
    suspend operator fun invoke(wallpaper: Wallpaper) {
        favoriteWallpapersRepository.changeWallpaperFavoriteStatus(wallpaper)
    }
}