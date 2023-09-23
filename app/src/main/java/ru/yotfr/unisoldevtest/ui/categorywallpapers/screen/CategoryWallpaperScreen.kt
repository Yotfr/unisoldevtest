package ru.yotfr.unisoldevtest.ui.categorywallpapers.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.ui.categories.util.displayName
import ru.yotfr.unisoldevtest.ui.categorywallpapers.viewmodel.CategoryWallpaperViewModel

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun CategoryWallpaperScreen(
    vm: CategoryWallpaperViewModel = hiltViewModel(),
    category: Category,
    navigateBack: () -> Unit,
    navigateToWallpaper: (Wallpaper) -> Unit
) {
    val wallpapers = vm.wallpapers.collectAsLazyPagingItems()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = wallpapers.loadState.refresh == LoadState.Loading,
        onRefresh = vm::refresh
    )

    LaunchedEffect(Unit) {
        vm.setCategory(category)
    }

    Scaffold(
        topBar = {
            CategoryWallpaperTopBar(
                navigateBack = navigateBack,
                title = category.displayName()
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .pullRefresh(pullRefreshState)
        ) {
            CategoryWallpaperContent(
                wallpapers = wallpapers,
                navigateToWallpaper = navigateToWallpaper,
                changeFavorite = vm::changeFavorite
            )
            PullRefreshIndicator(
                refreshing = wallpapers.loadState.refresh == LoadState.Loading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }

}

