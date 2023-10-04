package ru.yotfr.shared.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddToPhotos
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FileDownload
import androidx.compose.ui.graphics.vector.ImageVector
import ru.yotfr.resources.R

sealed class BottomBarScreens(
    val route: String,
    val icon: ImageVector,
    @StringRes val stringResId: Int
) {
    data object Categories : BottomBarScreens(
        route = "categories",
        icon = Icons.Outlined.AddToPhotos,
        stringResId = R.string.categories
    )

    data object FavoriteWallpapers: BottomBarScreens(
        route = "favorite_wallpapers",
        icon = Icons.Outlined.Favorite,
        stringResId = R.string.favorite
    )

    data object SavedWallpapers: BottomBarScreens(
        route = "saved_wallpapers",
        icon = Icons.Outlined.FileDownload,
        stringResId = R.string.saved
    )
}