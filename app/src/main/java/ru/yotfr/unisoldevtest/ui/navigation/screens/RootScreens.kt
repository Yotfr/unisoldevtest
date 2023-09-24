package ru.yotfr.unisoldevtest.ui.navigation.screens

import ru.yotfr.unisoldevtest.domain.model.Category

sealed class RootScreens(
    val route: String
) {
    companion object{
        const val CATEGORY_KEY = "CATEGORY"
        const val WALLPAPER_ID_KEY = "WALLPAPER_ID"
    }
    data object BottomNav : RootScreens(
        route = "bottom_nav"
    )

    data object WallpapersByCategory : RootScreens(
        route = "category_wallpapers/{$CATEGORY_KEY}"
    ) {
        fun passCategory(category: Category): String {
            return this.route.replace(
                oldValue = "{$CATEGORY_KEY}",
                newValue = category.name
            )
        }
    }

    data object WallpaperDetails : RootScreens(
        route = "wallpaper/{$WALLPAPER_ID_KEY}"
    ) {
        fun passId(id: String): String {
            return this.route.replace(
                oldValue = "{$WALLPAPER_ID_KEY}",
                newValue = id
            )
        }
    }

    data object Settings : RootScreens(
        route = "settings"
    )
}