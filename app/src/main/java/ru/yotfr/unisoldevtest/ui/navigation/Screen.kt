package ru.yotfr.unisoldevtest.ui.navigation

import ru.yotfr.unisoldevtest.R
import ru.yotfr.unisoldevtest.domain.model.Category

sealed class Screen(
    val route: String,
    val iconResId: Int? = null,
    val stringResId: Int? = null
) {
    data object Categories : Screen(
        route = "categories",
        iconResId = R.drawable.ic_categories,
        stringResId = R.string.categories
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

    data object FavoriteWallpapers: Screen(
        route = "favorite_wallpapers",
        iconResId = R.drawable.ic_favorite,
        stringResId = R.string.favorite
    )

    data object SavedWallpapers: Screen(
        route = "saved_wallpapers",
        iconResId = R.drawable.ic_saved,
        stringResId = R.string.saved
    )
}

val rootScreens = listOf(
    Screen.Categories,
    Screen.FavoriteWallpapers,
    Screen.SavedWallpapers
)