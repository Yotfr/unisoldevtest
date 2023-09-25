package ru.yotfr.unisoldevtest.ui.savedwallpapers.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.ui.common.displayText
import ru.yotfr.unisoldevtest.ui.savedwallpapers.event.SavedWallpapersEvent
import ru.yotfr.unisoldevtest.ui.savedwallpapers.event.SavedWallpapersScreenEvent
import ru.yotfr.unisoldevtest.ui.savedwallpapers.viewmodel.SavedWallpaperViewModel
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun SavedWallpaperScreen(
    vm: SavedWallpaperViewModel = hiltViewModel(),
    navigateToWallpaper: (String) -> Unit
) {

    val state by vm.state.collectAsState()
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current
    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { vm.onEvent(SavedWallpapersEvent.PullRefresh) }
    )

    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            vm.event.collect { screenEvent ->
                when(screenEvent) {
                    is SavedWallpapersScreenEvent.ShowErrorToast -> {
                        scope.launch {
                            snackBarHostState.showSnackbar(
                                message = screenEvent.error.displayText(context),
                                duration = SnackbarDuration.Long
                            )
                        }
                    }
                }
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) {
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
                items(state.wallpapers) { savedImage ->
                    SavedWallpaperItem(
                        downloadedImage = savedImage,
                        onClick = navigateToWallpaper
                    )
                }
            }
            PullRefreshIndicator(
                refreshing = state.isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}