package ru.yotfr.unisoldevtest.ui.wallpaper.screen

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import coil.compose.AsyncImage
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.R
import ru.yotfr.unisoldevtest.ui.common.displayText
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperEvent
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperScreenEvent
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
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    // Needed to dismiss Indefinite Snackbar
    var job: Job? by remember {
        mutableStateOf(null)
    }
    var isShowInstallDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        vm.onEvent(WallpaperEvent.EnteredScreen(wallpaperId = id))
    }

    LaunchedEffect(Unit) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            vm.event.collect { screenEvent ->
                when (screenEvent) {
                    WallpaperScreenEvent.ShowDownloadCompleteSnackbar -> {
                        job?.cancel()
                        job = scope.launch {
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.saved),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }

                    WallpaperScreenEvent.ShowDownloadFailedProgressSnackbar -> {
                        job?.cancel()
                        job = scope.launch {
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.failed),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }

                    WallpaperScreenEvent.ShowDownloadInProgressSnackbar -> {
                        job?.cancel()
                        job = scope.launch {
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.saving),
                                duration = SnackbarDuration.Indefinite
                            )
                        }
                    }

                    WallpaperScreenEvent.ShowFileAlreadySavedSnackbar -> {
                        job?.cancel()
                        job = scope.launch {
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.already_saved),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }

                    WallpaperScreenEvent.ShowInstallCompletedSnackbar -> {
                        job?.cancel()
                        job = scope.launch {
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.applied),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }

                    WallpaperScreenEvent.ShowInstallFailedSnackbar -> {
                        job?.cancel()
                        job = scope.launch {
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.failed),
                                duration = SnackbarDuration.Short
                            )
                        }
                    }

                    WallpaperScreenEvent.ShowInstallInProgressSnackbar -> {
                        job?.cancel()
                        job = scope.launch {
                            snackBarHostState.showSnackbar(
                                message = context.getString(R.string.applying),
                                duration = SnackbarDuration.Indefinite
                            )
                        }
                    }

                    is WallpaperScreenEvent.ShowErrorSnackbar -> {
                        job?.cancel()
                        job = scope.launch {
                            snackBarHostState.showSnackbar(
                                message = screenEvent.errorCause.displayText(context),
                                duration = SnackbarDuration.Long
                            )
                        }
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

    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            WallpaperTopBar(
                onArrowBackPressed = navigateBack,
                isVisible = state.isBarsVisible,
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        bottomBar = {
            WallpaperBottomButtonBar(
                isVisible = state.isBarsVisible,
                onSaveClicked = { vm.onEvent(WallpaperEvent.DownloadWallpaper) },
                onApplyClicked = { isShowInstallDialog = true },
                onFavoriteClicked = { vm.onEvent(WallpaperEvent.ChangeFavoriteStatus) },
                isFavorite = state.wallpaper?.isFavorite ?: false,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    ) {
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
            AnimatedVisibility(
                visible = isShowInstallDialog,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                InstallOptionsDialog(
                    onDismiss = { isShowInstallDialog = false },
                    onOkPressed = { installOption ->
                        isShowInstallDialog = false
                        vm.onEvent(
                            WallpaperEvent.InstallWallpaper(
                                wallpaperInstallOption = installOption
                            )
                        )
                    }
                )
            }
        }
    }
}



