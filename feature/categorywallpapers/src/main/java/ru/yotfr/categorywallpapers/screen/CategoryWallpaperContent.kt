package ru.yotfr.categorywallpapers.screen

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import ru.yotfr.shared.WallpaperItem
import ru.yotfr.designsystem.theme.WallpaperTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryWallpaperContent(
    wallpapers: LazyPagingItems<ru.yotfr.model.Wallpaper>,
    navigateToWallpaper: (ru.yotfr.model.Wallpaper) -> Unit,
    changeFavorite: (ru.yotfr.model.Wallpaper) -> Unit,
    showToast: (String) -> Unit,
    context: Context
) {
    LazyVerticalStaggeredGrid(
        columns = if (
            LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        ) {
            StaggeredGridCells.Adaptive(175.dp)
        } else StaggeredGridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = ru.yotfr.designsystem.theme.WallpaperTheme.spacing.medium),
        verticalItemSpacing = ru.yotfr.designsystem.theme.WallpaperTheme.spacing.small,
        horizontalArrangement = Arrangement.spacedBy(ru.yotfr.designsystem.theme.WallpaperTheme.spacing.small)
    ) {
        item(span = StaggeredGridItemSpan.FullLine) {
            Spacer(modifier = Modifier.height(ru.yotfr.designsystem.theme.WallpaperTheme.spacing.medium))
        }
        items(
            count = wallpapers.itemCount,
            key = wallpapers.itemKey { it.id }
        ) { index ->
            wallpapers[index]?.let { wallpaper ->
                ru.yotfr.shared.WallpaperItem(
                    wallpaper = wallpaper,
                    onClick = navigateToWallpaper,
                    onFavoriteClicked = changeFavorite,
                    context = context
                )
            }
        }
        if (wallpapers.loadState.append == LoadState.Loading) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center),
                        color = ru.yotfr.designsystem.theme.WallpaperTheme.extraColors.onWallpaperText
                    )
                }
            }
        }
        item(span = StaggeredGridItemSpan.FullLine) {
            Spacer(modifier = Modifier.height(ru.yotfr.designsystem.theme.WallpaperTheme.spacing.medium))
        }
    }
    if (wallpapers.loadState.refresh is LoadState.Error) {
        showToast(
            (wallpapers.loadState.refresh as LoadState.Error).error.message
                ?: "Somethings went wrong"
        )
    }
}