package ru.yotfr.categorywallpapers.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Scaffold
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.launch
import ru.yotfr.categorywallpapers.event.CategoryWallpapersEvent
import ru.yotfr.categorywallpapers.viewmodel.CategoryWallpaperViewModel
import ru.yotfr.shared.mapper.displayName
import ru.yotfr.shared.model.Category
import ru.yotfr.shared.model.Wallpaper

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
internal fun CategoryWallpaperScreen(
    vm: CategoryWallpaperViewModel = hiltViewModel(),
    category: Category,
    navigateBack: () -> Unit,
    navigateToWallpaper: (Wallpaper) -> Unit
) {
    val wallpapers = vm.wallpapers.collectAsLazyPagingItems()
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    val pullRefreshState = rememberPullRefreshState(
        refreshing = wallpapers.loadState.refresh == LoadState.Loading,
        onRefresh = { vm.onEvent(CategoryWallpapersEvent.PullRefresh) }
    )

    LaunchedEffect(Unit) {
        vm.onEvent(CategoryWallpapersEvent.SetCategory(category))
    }

    Scaffold(
        topBar = {
            CategoryWallpaperTopBar(
                navigateBack = navigateBack,
                title = category.displayName()
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
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
                changeFavorite = { wallpaper ->
                    vm.onEvent(
                        CategoryWallpapersEvent.ChangeFavorite(
                            wallpaper = wallpaper
                        )
                    )
                },
                showToast = { message ->
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            message = message,
                            duration = SnackbarDuration.Long
                        )
                    }
                },
                context = context
            )
            PullRefreshIndicator(
                refreshing = wallpapers.loadState.refresh == LoadState.Loading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }

}

