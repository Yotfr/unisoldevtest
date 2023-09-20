package ru.yotfr.unisoldevtest.ui.categories.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.ui.categories.viewmodel.CategoriesViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreen(
    vm: CategoriesViewModel = hiltViewModel(),
    navigateToCategoryWallpaper: (Category) -> Unit
) {
    val state by vm.state.collectAsState()

    val pullRefreshState = rememberPullRefreshState(
        refreshing = state.isLoading,
        onRefresh = { vm.refresh() }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(state.categories) { categoryModel ->
                CategoryItem(categoryModel = categoryModel)
            }
        }
        PullRefreshIndicator(refreshing = state.isLoading, state = pullRefreshState)
    }
}

@Composable
fun CategoryItem(categoryModel: CategoryModel) {
    Box() {
        AsyncImage(
            modifier = Modifier.fillMaxWidth(),
            model = categoryModel.previewUrl,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(start = 8.dp, end = 8.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: change name
            Text(
                text = categoryModel.category.name.lowercase()
            )
            Text(
                text = categoryModel.wallpapersCount.toString()
            )
        }
    }
}