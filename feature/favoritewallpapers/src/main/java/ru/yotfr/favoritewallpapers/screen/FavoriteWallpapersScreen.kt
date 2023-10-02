package ru.yotfr.favoritewallpapers.screen

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.designsystem.theme.WallpaperTheme
import ru.yotfr.resources.R
import ru.yotfr.favoritewallpapers.event.FavoriteWallpapersEvent
import ru.yotfr.favoritewallpapers.viewmodel.FavoriteWallpapersViewModel
import ru.yotfr.model.Wallpaper
import ru.yotfr.shared.component.WallpaperItem

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteWallpapersScreen(
    vm: FavoriteWallpapersViewModel = hiltViewModel(),
    navigateToWallpaper: (Wallpaper) -> Unit
) {
    val wallpapers by vm.favoriteWallpapers.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyVerticalStaggeredGrid(
            columns = if (
                LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
            ) {
                StaggeredGridCells.Adaptive(175.dp)
            } else StaggeredGridCells.Fixed(2),
            modifier = Modifier.padding(horizontal = WallpaperTheme.spacing.medium),
            verticalItemSpacing = WallpaperTheme.spacing.small,
            horizontalArrangement = Arrangement.spacedBy(WallpaperTheme.spacing.small)
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Spacer(modifier = Modifier.height(WallpaperTheme.spacing.medium))
            }
            items(wallpapers) { wallpaper ->
                WallpaperItem(
                    wallpaper = wallpaper,
                    onClick = navigateToWallpaper,
                    onFavoriteClicked = { wall ->
                        vm.onEvent(
                            FavoriteWallpapersEvent.ChangeFavorite(
                                wallpaper = wall
                            )
                        )
                    },
                    context = context
                )
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                Spacer(modifier = Modifier.height(WallpaperTheme.spacing.medium))
            }
        }
        if (wallpapers.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.no_favorites),
                    style = WallpaperTheme.typography.title
                )
            }
        }
    }
}

