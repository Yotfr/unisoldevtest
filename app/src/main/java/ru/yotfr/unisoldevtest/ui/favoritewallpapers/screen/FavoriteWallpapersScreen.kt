package ru.yotfr.unisoldevtest.ui.favoritewallpapers.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.ui.common.WallpaperItem
import ru.yotfr.unisoldevtest.ui.favoritewallpapers.viewmodel.FavoriteWallpapersViewModel
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteWallpapersScreen(
    vm: FavoriteWallpapersViewModel = hiltViewModel(),
    navigateToWallpaper: (Wallpaper) -> Unit
) {
    val wallpapers by vm.favoriteWallpapers.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(WallpaperTheme.spacing.medium),
            verticalItemSpacing = WallpaperTheme.spacing.small,
            horizontalArrangement = Arrangement.spacedBy(WallpaperTheme.spacing.small)
        ) {
            items(wallpapers) { wallpaper ->
                WallpaperItem(
                    wallpaper = wallpaper,
                    onClick = navigateToWallpaper,
                    onFavoriteClicked = vm::changeFavorite
                )
            }
        }
    }
}

