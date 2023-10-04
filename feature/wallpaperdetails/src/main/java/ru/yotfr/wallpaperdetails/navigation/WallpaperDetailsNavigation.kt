package ru.yotfr.wallpaperdetails.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.yotfr.shared.navigation.RootScreens
import ru.yotfr.wallpaperdetails.screen.WallpaperScreen

fun NavGraphBuilder.wallpaperDetailsScreen(
    navigateBack: () -> Unit
) {
    composable(
        route = RootScreens.WallpaperDetails.route,
        arguments = listOf(
            navArgument(RootScreens.WALLPAPER_ID_KEY) {}
        )
    ) { backStackEntry ->
        val id = backStackEntry.arguments?.getString(
            RootScreens.WALLPAPER_ID_KEY
        ) ?: throw IllegalArgumentException("Navigated with wrong WallpaperID")
        WallpaperScreen(
            id = id,
            navigateBack = navigateBack
        )
    }
}