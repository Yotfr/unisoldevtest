package ru.yotfr.unisoldevtest.ui.categories.screen

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.ui.categories.state.CategoriesScreenState
import ru.yotfr.unisoldevtest.ui.theme.WallpaperTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoriesContent(
    state: CategoriesScreenState,
    onCategoryClicked: (Category) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = if (
            LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
        ) {
            StaggeredGridCells.Adaptive(175.dp)
        } else StaggeredGridCells.Fixed(2),
        modifier = Modifier.padding(WallpaperTheme.spacing.medium),
        verticalItemSpacing = WallpaperTheme.spacing.small,
        horizontalArrangement = Arrangement.spacedBy(WallpaperTheme.spacing.small)
    ) {
        items(
            items = state.categories,
            key = { it.category }
        ) { categoryModel ->
            CategoryItem(
                categoryModel = categoryModel,
                onClick = onCategoryClicked
            )
        }
    }
}