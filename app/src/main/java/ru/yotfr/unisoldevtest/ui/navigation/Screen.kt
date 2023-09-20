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
}