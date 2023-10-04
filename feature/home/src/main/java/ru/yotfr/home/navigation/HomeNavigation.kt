package ru.yotfr.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.yotfr.home.screen.HomeScreen
import ru.yotfr.shared.navigation.RootScreens

fun NavGraphBuilder.homeScreen(
    navigateToSettings: () -> Unit,
    navigateToWallpapersByCategory: (categoryName: String) -> Unit,
    navigateToWallpaperDetails: (wallpaperId: String) -> Unit
) {
    composable(
        route = RootScreens.Home.route
    ) {
        HomeScreen(
            navigateToSettings = navigateToSettings,
            navigateToWallpapersByCategory = navigateToWallpapersByCategory,
            navigateToWallpaperDetails = navigateToWallpaperDetails
        )
    }
}