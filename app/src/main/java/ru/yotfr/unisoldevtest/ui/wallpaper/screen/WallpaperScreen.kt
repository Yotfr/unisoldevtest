package ru.yotfr.unisoldevtest.ui.wallpaper.screen

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.collectLatest
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperEvent
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperScreenEvent
import ru.yotfr.unisoldevtest.ui.wallpaper.viewmodel.WallpaperViewModel

@Composable
fun WallpaperScreen(
    vm: WallpaperViewModel = hiltViewModel(),
    id: String,
    navigateBack: () -> Unit
) {
    val state by vm.state.collectAsState()
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    LaunchedEffect(Unit) {
        vm.onEvent(WallpaperEvent.EnteredScreen(wallpaperId = id))
    }

    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            vm.event.collectLatest { screenEvent ->
                when(screenEvent) {
                    WallpaperScreenEvent.ShowDownloadCompleteSnackbar -> {
                        Toast.makeText(context, "DOWNLOADCOMPLETED", Toast.LENGTH_SHORT).show()
                    }
                    WallpaperScreenEvent.ShowDownloadFailedProgressSnackbar -> {
                        Toast.makeText(context, "DOWNLOADFAILED", Toast.LENGTH_SHORT).show()
                    }
                    WallpaperScreenEvent.ShowDownloadInProgressSnackbar -> {
                        Toast.makeText(context, "DOWNLOADINPROGRESS", Toast.LENGTH_SHORT).show()
                    }
                    WallpaperScreenEvent.ShowFileAlreadySavedSnackbar -> {
                        Toast.makeText(context, "FILEALREADYSAVED", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    DisposableEffect(Unit) {
        val intentFilter = IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE)
        val receiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val downloadId = intent.getLongExtra(
                    DownloadManager.EXTRA_DOWNLOAD_ID,
                    -1L
                )
                vm.onEvent(WallpaperEvent.DownloadCompleted(downloadId = downloadId))
            }
        }
        ContextCompat.registerReceiver(
            context, receiver, intentFilter, ContextCompat.RECEIVER_EXPORTED
        )
        onDispose {
            context.unregisterReceiver(receiver)
        }
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
                    vm.onEvent(WallpaperEvent.ChangeBarsVisibility)
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
            onSaveClicked = { vm.onEvent(WallpaperEvent.DownloadWallpaper) },
            onApplyClicked = { /*TODO*/ },
            onDeleteClicked = { /*TODO*/ },
            isFavorite = state.wallpaper?.isFavorite ?: false,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        )
    }
}



