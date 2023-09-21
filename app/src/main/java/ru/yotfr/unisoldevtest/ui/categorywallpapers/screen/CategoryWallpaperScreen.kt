package ru.yotfr.unisoldevtest.ui.categorywallpapers.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.ui.categorywallpapers.viewmodel.CategoryWallpaperViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryWallpaperScreen(
    vm: CategoryWallpaperViewModel = hiltViewModel(),
    category: Category
) {
    val wallpapers = vm.wallpapers.collectAsLazyPagingItems()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = wallpapers.loadState.refresh == LoadState.Loading,
        onRefresh = { vm.refresh() }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyColumn {
            items(
                count = wallpapers.itemCount,
                key = wallpapers.itemKey { it.id }
            ) { index ->
                val item = wallpapers[index]
                item?.let {
                    AsyncImage(
                        modifier = Modifier.fillMaxWidth(),
                        model = it.previewUrl,
                        contentDescription = null,
                        contentScale = ContentScale.Fit
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
            state = pullRefreshState
        )
    }

    LaunchedEffect(Unit) {
        vm.setCategory(category)
    }
}

@Composable
fun WallpaperItem(wallpaper: Wallpaper) {
    Box() {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = wallpaper.previewUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}