package ru.yotfr.unisoldevtest.ui.wallpaper.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.yotfr.unisoldevtest.ui.wallpaper.viewmodel.WallpaperViewModel

@Composable
fun WallpaperScreen(
    vm: WallpaperViewModel = hiltViewModel(),
    id: String,
    navigateBack: () -> Unit
) {
    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.changeId(id)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxSize(),
            visible = state.wallpaper != null,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            AsyncImage(
                modifier = Modifier.clickable {
                    vm.changeButtonsRowVisibility()
                },
                model = state.wallpaper?.url ?: "",
                contentDescription = null,
                contentScale = ContentScale.FillHeight
            )
        }
        WallpaperTopBar(
            onArrowBackPressed = navigateBack,
            isVisible = state.isBarsVisible,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        )
        WallpaperBottomButtonBar(
            isVisible = state.isBarsVisible,
            onSaveClicked = vm::downloadWallpaper,
            onApplyClicked = { /*TODO*/ },
            onDeleteClicked = { /*TODO*/ },
            isFavorite = state.wallpaper?.isFavorite ?: false,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}



