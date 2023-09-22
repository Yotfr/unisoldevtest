package ru.yotfr.unisoldevtest.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.yotfr.unisoldevtest.ui.categories.screen.CategoriesScreen
import ru.yotfr.unisoldevtest.ui.favoritewallpapers.screen.FavoriteWallpapersScreen
import ru.yotfr.unisoldevtest.ui.navigation.screens.BottomBarScreens
import ru.yotfr.unisoldevtest.ui.navigation.screens.WallpaperDetailsScreens

@Composable
fun HomeNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graphs.BOTTOM_NAV_BAR,
        startDestination = BottomBarScreens.Categories.route
    ) {
        composable(
            route = BottomBarScreens.Categories.route
        ) {
            CategoriesScreen(
                navigateToCategoryWallpaper = { category ->
                    navController.navigate(
                        WallpaperDetailsScreens.WallpapersByCategory.passCategory(
                            category
                        )
                    )
                }
            )
        }
        composable(
            route = BottomBarScreens.FavoriteWallpapers.route
        ) {
            FavoriteWallpapersScreen(
                navigateToWallpaper = {
                    navController.navigate(
                        WallpaperDetailsScreens.WallpaperDetails.passId(
                            it.id
                        )
                    )
                }
            )
        }
        composable(
            route = BottomBarScreens.SavedWallpapers.route
        ) {
            // TODO: Saved wallpapers
        }
        wallpaperDetailsNavGraph(navController)
    }
}