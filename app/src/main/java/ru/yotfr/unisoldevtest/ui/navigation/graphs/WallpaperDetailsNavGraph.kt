package ru.yotfr.unisoldevtest.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.ui.categorywallpapers.screen.CategoryWallpaperScreen
import ru.yotfr.unisoldevtest.ui.navigation.screens.WallpaperDetailsScreens
import ru.yotfr.unisoldevtest.ui.wallpaper.screen.WallpaperScreen

fun NavGraphBuilder.wallpaperDetailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graphs.WALLPAPER_DETAILS,
        startDestination = WallpaperDetailsScreens.WallpapersByCategory.route
    ) {
        composable(
            route = WallpaperDetailsScreens.WallpapersByCategory.route,
            arguments = listOf(
                navArgument(WallpaperDetailsScreens.CATEGORY_KEY) {}
            )
        ) { backStackEntry ->
            val category = Category.valueOf(
                backStackEntry.arguments?.getString(
                    WallpaperDetailsScreens.CATEGORY_KEY
                ) ?: throw IllegalArgumentException("Navigated with wrong Category")
            )
            CategoryWallpaperScreen(
                category = category,
                navigateBack = { navController.popBackStack() },
                navigateToWallpaper = { wallpaper ->
                    navController.navigate(
                        WallpaperDetailsScreens.WallpaperDetails.passId(
                            wallpaper.id
                        )
                    )
                }
            )
        }
        composable(
            route = WallpaperDetailsScreens.WallpaperDetails.route,
            arguments = listOf(
                navArgument(WallpaperDetailsScreens.WALLPAPER_ID_KEY) {}
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString(
                WallpaperDetailsScreens.WALLPAPER_ID_KEY
            ) ?: throw IllegalArgumentException("Navigated with wrong WallpaperID")
            WallpaperScreen(
                id = id,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}