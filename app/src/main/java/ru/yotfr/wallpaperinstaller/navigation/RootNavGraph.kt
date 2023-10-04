package ru.yotfr.wallpaperinstaller.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.yotfr.categorywallpapers.navigation.categoryWallpapersScreen
import ru.yotfr.home.navigation.homeScreen
import ru.yotfr.settings.navigation.settingsScreen
import ru.yotfr.shared.navigation.RootScreens
import ru.yotfr.wallpaperdetails.navigation.wallpaperDetailsScreen

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = RootScreens.Home.route
    ) {
        homeScreen(
            navigateToSettings = {
                navController.navigate(
                    RootScreens.Settings.route
                )
            },
            navigateToWallpapersByCategory = { categoryName ->
                navController.navigate(
                    RootScreens.WallpapersByCategory.passCategory(
                        categoryName = categoryName
                    )
                )
            },
            navigateToWallpaperDetails = { wallpaperId ->
                navController.navigate(
                    RootScreens.WallpaperDetails.passId(
                        id = wallpaperId
                    )
                )
            }
        )
        categoryWallpapersScreen(
            navigateBack = {
                navController.popBackStack()
            },
            navigateToWallpaper = { wallpaperId ->
                navController.navigate(
                    RootScreens.WallpaperDetails.passId(
                        id = wallpaperId
                    )
                )
            }
        )
        wallpaperDetailsScreen(
            navigateBack = {
                navController.popBackStack()
            }
        )
        settingsScreen(
            navigateBack = {
                navController.popBackStack()
            }
        )
    }
}