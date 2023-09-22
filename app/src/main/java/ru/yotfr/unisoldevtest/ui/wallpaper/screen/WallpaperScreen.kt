package ru.yotfr.unisoldevtest.ui.wallpaper.screen

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.Window
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.DialogWindowProvider
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.yotfr.unisoldevtest.ui.wallpaper.viewmodel.WallpaperViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WallpaperScreen(
    vm: WallpaperViewModel = hiltViewModel(),
    id: String,
    navigateBack: () -> Unit
) {
    val state by vm.state.collectAsState()
    val window = (LocalView.current.parent as? DialogWindowProvider)?.window ?:LocalView.current.context.findWindow()
    val windowInsetsController = remember {
        window?.let {
            WindowCompat.getInsetsController(window, window.decorView)
        }
    }

    LaunchedEffect(Unit) {
        vm.changeId(id)
    }

    Scaffold(
        topBar = {
            WallpaperTopBar(
                onArrowBackPressed = navigateBack,
                isVisible = state.isBarsVisible
            )
        },
        bottomBar = {
            WallpaperBottomAppBar(
                isVisible = state.isBarsVisible,
                onSaveClicked = { /*TODO*/ },
                onApplyClicked = { /*TODO*/ },
                onDeleteClicked = { /*TODO*/ },
                isFavorite = state.wallpaper?.isFavorite ?: false
            )
        }
    ) {
        state.wallpaper?.url?.let { url ->
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        vm.changeButtonsRowVisibility()
                        if (state.isBarsVisible) {
                            windowInsetsController?.hide(WindowInsetsCompat.Type.systemBars())
                            window?.statusBarColor = android.graphics.Color.TRANSPARENT
                            window?.navigationBarColor = android.graphics.Color.TRANSPARENT
                        } else {
                            windowInsetsController?.show(WindowInsetsCompat.Type.systemBars())
                        }
                    },
                contentScale = ContentScale.FillHeight
            )
        }
    }
}

private tailrec fun Context.findWindow(): Window? =
    when (this) {
        is Activity -> window
        is ContextWrapper -> baseContext.findWindow()
        else -> null
    }

