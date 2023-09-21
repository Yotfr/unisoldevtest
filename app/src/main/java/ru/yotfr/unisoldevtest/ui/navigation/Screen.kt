package ru.yotfr.unisoldevtest.ui.navigation

import ru.yotfr.unisoldevtest.domain.model.Category

sealed class Screen(val route: String) {
    data object Categories : Screen(
        route = "categories"
    )
    data object CategoryWallpapers : Screen(
        route = "category_wallpapers/{${NavigationConstants.CATEGORY_KEY}}"
    ) {
        fun passCategory(category: Category): String {
            return this.route.replace(
                oldValue = "{${NavigationConstants.CATEGORY_KEY}}",
                newValue = category.name
            )
        }
    }

    data object Wallpaper : Screen(
        route = "wallpaper/{${NavigationConstants.WALLPAPER_ID_KEY}}"
    ) {
        fun passId(id: String): String {
            return this.route.replace(
                oldValue = "{${NavigationConstants.WALLPAPER_ID_KEY}}",
                newValue = id
            )
        }
    }
}