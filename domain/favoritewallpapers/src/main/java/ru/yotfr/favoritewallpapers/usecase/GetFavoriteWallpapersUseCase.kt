package ru.yotfr.favoritewallpapers.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.favoritewallpapers.repository.FavoriteWallpapersRepository
import ru.yotfr.shared.model.Wallpaper
import javax.inject.Inject

class GetFavoriteWallpapersUseCase @Inject constructor(
    private val favoriteWallpapersRepository: FavoriteWallpapersRepository
) {
    operator fun invoke(): Flow<List<Wallpaper>> =
        favoriteWallpapersRepository.getFavoriteWallpapers()
}