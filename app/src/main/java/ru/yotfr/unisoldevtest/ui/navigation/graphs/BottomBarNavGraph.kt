package ru.yotfr.unisoldevtest.ui.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.categories.screen.CategoriesScreen
import ru.yotfr.favoritewallpapers.screen.FavoriteWallpapersScreen
import ru.yotfr.unisoldevtest.ui.navigation.screens.BottomBarScreens
import ru.yotfr.unisoldevtest.ui.navigation.screens.RootScreens
import ru.yotfr.savedwallpapers.screen.SavedWallpaperScreen

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
            com.example.categories.screen.CategoriesScreen(
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
            ru.yotfr.favoritewallpapers.screen.FavoriteWallpapersScreen(
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
            ru.yotfr.savedwallpapers.screen.SavedWallpaperScreen(
                navigateToWallpaper = { wallaperId ->
                    navController.navigate(
                        RootScreens.WallpaperDetails.passId(
                            wallaperId
                        )
                    )
                }
            )
        }
    }
}