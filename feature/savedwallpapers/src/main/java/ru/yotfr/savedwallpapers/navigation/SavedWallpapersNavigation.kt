package ru.yotfr.savedwallpapers.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.yotfr.savedwallpapers.screen.SavedWallpaperScreen
import ru.yotfr.shared.navigation.BottomBarScreens

fun NavGraphBuilder.savedWallpapersScreen(
    navigateToWallpaperDetails: (wallpaperId: String) -> Unit
) {
    composable(
        route = BottomBarScreens.SavedWallpapers.route
    ) {
        SavedWallpaperScreen(
            navigateToWallpaper = { wallpaperId ->
                navigateToWallpaperDetails(wallpaperId)
            }
        )
    }
}