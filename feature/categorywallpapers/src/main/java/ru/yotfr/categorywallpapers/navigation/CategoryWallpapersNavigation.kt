package ru.yotfr.categorywallpapers.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.yotfr.categorywallpapers.screen.CategoryWallpaperScreen
import ru.yotfr.shared.model.Category
import ru.yotfr.shared.navigation.RootScreens

fun NavGraphBuilder.categoryWallpapersScreen(
    navigateBack: () -> Unit,
    navigateToWallpaper: (wallpaperId: String) -> Unit
) {
    composable(
        route = RootScreens.WallpapersByCategory.route,
        arguments = listOf(
            navArgument(RootScreens.CATEGORY_KEY) {}
        )
    ) { backStackEntry ->
        val category = Category.valueOf(
            backStackEntry.arguments?.getString(
                RootScreens.CATEGORY_KEY
            ) ?: throw IllegalArgumentException("Navigated with wrong Category")
        )
        CategoryWallpaperScreen(
            category = category,
            navigateBack = navigateBack,
            navigateToWallpaper = { wallpaper ->
                navigateToWallpaper(wallpaper.id)
            }
        )
    }
}