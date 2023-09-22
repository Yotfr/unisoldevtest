package ru.yotfr.unisoldevtest.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.ui.categories.screen.CategoriesScreen
import ru.yotfr.unisoldevtest.ui.categorywallpapers.screen.CategoryWallpaperScreen
import ru.yotfr.unisoldevtest.ui.favoritewallpapers.screen.FavoriteWallpapersScreen
import ru.yotfr.unisoldevtest.ui.wallpaper.screen.WallpaperScreen

@Composable
fun WallpaperNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Categories.route,
        modifier = modifier
    ) {
        composable(
            route = Screen.Categories.route
        ) {
            CategoriesScreen(
                navigateToCategoryWallpaper = { category ->
                    navController.navigate(Screen.CategoryWallpapers.passCategory(category))
                }
            )
        }
        composable(
            route = Screen.CategoryWallpapers.route,
            arguments = listOf(
                navArgument(NavigationConstants.CATEGORY_KEY) {}
            )
        ) { backStackEntry ->
            val category = Category.valueOf(
                backStackEntry.arguments?.getString(
                    NavigationConstants.CATEGORY_KEY
                ) ?: throw IllegalArgumentException("Navigated with wrong Category")
            )
            CategoryWallpaperScreen(category = category)
        }

        composable(
            route = Screen.Wallpaper.route,
            arguments = listOf(
                navArgument(NavigationConstants.WALLPAPER_ID_KEY) {}
            )
        ) {backStackEntry ->
            val id = backStackEntry.arguments?.getString(
                NavigationConstants.WALLPAPER_ID_KEY
            ) ?: throw IllegalArgumentException("Navigated with wrong WallpaperID")
            WallpaperScreen(id = id)
        }

        composable(
            route = Screen.FavoriteWallpapers.route
        ) {
            FavoriteWallpapersScreen()
        }
    }
}