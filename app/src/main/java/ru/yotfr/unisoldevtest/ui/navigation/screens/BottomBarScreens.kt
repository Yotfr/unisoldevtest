package ru.yotfr.unisoldevtest.ui.navigation.screens

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddToPhotos
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.ui.graphics.vector.ImageVector
import ru.yotfr.unisoldevtest.R

sealed class BottomBarScreens(
    val route: String,
    val icon: ImageVector,
    @StringRes val stringResId: Int
) {
    data object Categories : BottomBarScreens(
        route = "CATEGORIES",
        icon = Icons.Outlined.AddToPhotos,
        stringResId = R.string.categories
    )

    data object FavoriteWallpapers: BottomBarScreens(
        route = "FAVORITE_WALLPAPERS",
        icon = Icons.Outlined.Favorite,
        stringResId = R.string.favorite
    )

    data object SavedWallpapers: BottomBarScreens(
        route = "SAVED_WALLPAPERS",
        icon = Icons.Outlined.FileDownload,
        stringResId = R.string.saved
    )
}