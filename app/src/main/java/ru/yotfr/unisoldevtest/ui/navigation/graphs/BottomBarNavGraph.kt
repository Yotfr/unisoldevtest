package ru.yotfr.unisoldevtest.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ru.yotfr.unisoldevtest.ui.categories.screen.CategoriesScreen
import ru.yotfr.unisoldevtest.ui.favoritewallpapers.screen.FavoriteWallpapersScreen
import ru.yotfr.unisoldevtest.ui.navigation.screens.BottomBarScreens
import ru.yotfr.unisoldevtest.ui.navigation.screens.RootScreens

fun NavGraphBuilder.bottomBarNavGraph(
    navController: NavHostController
) {
    navigation(
        route = RootScreens.BottomNav.route,
        startDestination = BottomBarScreens.Categories.route
    ) {
        composable(
            route = BottomBarScreens.Categories.route
        ) {
            CategoriesScreen(
                navigateToCategoryWallpaper = { category ->
                    navController.navigate(
                        RootScreens.WallpapersByCategory.passCategory(
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
                        RootScreens.WallpaperDetails.passId(
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
    }
}