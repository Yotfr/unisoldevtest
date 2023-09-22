package ru.yotfr.unisoldevtest.ui.categorywallpapers.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.ui.categorywallpapers.viewmodel.CategoryWallpaperViewModel
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun CategoryWallpaperScreen(
    vm: CategoryWallpaperViewModel = hiltViewModel(),
    category: Category
) {
    val wallpapers = vm.wallpapers.collectAsLazyPagingItems()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = wallpapers.loadState.refresh == LoadState.Loading,
        onRefresh = vm::refresh
    )

    LaunchedEffect(Unit) {
        vm.setCategory(category)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(WallpaperTheme.spacing.medium),
            verticalItemSpacing = WallpaperTheme.spacing.small,
            horizontalArrangement = Arrangement.spacedBy(WallpaperTheme.spacing.small)
        ) {
            items(
                count = wallpapers.itemCount,
                key = wallpapers.itemKey { it.id }
            ) { index ->
                val item = wallpapers[index]
                item?.let { wallpaper ->
                    CategoryWallpaperItem(
                        wallpaper = wallpaper,
                        onClick = {},
                        onFavoriteClicked = vm::changeFavorite
                    )
                }
            }
            if (wallpapers.loadState.append == LoadState.Loading) {
                item {
                    CircularProgressIndicator()
                }
            }
        }
        if (wallpapers.loadState.refresh == LoadState.Loading) {
            // TOOD:Error
        }
        PullRefreshIndicator(
            refreshing = wallpapers.loadState.refresh == LoadState.Loading,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

