package ru.yotfr.favoritewallpapers.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.yotfr.favoritewallpapers.screen.FavoriteWallpapersScreen
import ru.yotfr.shared.navigation.BottomBarScreens

fun NavGraphBuilder.favoriteWallpapersScreen(
    navigateToWallpaperDetails: (wallpaperId: String) -> Unit
) {
    composable(
        route = BottomBarScreens.FavoriteWallpapers.route
    ) {
        FavoriteWallpapersScreen(
            navigateToWallpaper = { wallpaper ->
                navigateToWallpaperDetails(wallpaper.id)
            }
        )
    }
}