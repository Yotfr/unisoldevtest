package com.example.categories.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.categories.screen.CategoriesScreen
import ru.yotfr.shared.navigation.BottomBarScreens
fun NavGraphBuilder.categoriesScreen(
    navigateToWallpapersByCategory: (categoryName: String) -> Unit,
) {
    composable(
        route = BottomBarScreens.Categories.route
    ) {
        CategoriesScreen(
            navigateToCategoryWallpaper = { category ->
                navigateToWallpapersByCategory(category.name)
            }
        )
    }
}