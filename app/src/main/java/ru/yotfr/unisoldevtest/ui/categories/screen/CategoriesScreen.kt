package ru.yotfr.unisoldevtest.ui.categories.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.ui.categories.viewmodel.CategoriesViewModel
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme


// TODO: LAST ELEMENT CUTS
@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun CategoriesScreen(
    vm: CategoriesViewModel = hiltViewModel(),
    navigateToCategoryWallpaper: (Category) -> Unit
) {
    val state by vm.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = vm::refresh
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.background)
            .pullRefresh(pullRefreshState)
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(150.dp),
            contentPadding = PaddingValues(WallpaperTheme.spacing.medium),
            verticalItemSpacing = WallpaperTheme.spacing.small,
            horizontalArrangement = Arrangement.spacedBy(WallpaperTheme.spacing.small)
        ) {
            items(
                items = state.categories,
                key = { it.category }
            ) { categoryModel ->
                CategoryItem(
                    categoryModel = categoryModel,
                    onClick = navigateToCategoryWallpaper
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