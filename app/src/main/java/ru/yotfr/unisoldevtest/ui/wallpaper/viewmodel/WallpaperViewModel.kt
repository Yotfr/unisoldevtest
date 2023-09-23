package ru.yotfr.unisoldevtest.ui.wallpaper.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.model.DownloadStatus
import ru.yotfr.unisoldevtest.domain.model.MResponse
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.usecase.CheckIfFileExistsUseCase
import ru.yotfr.unisoldevtest.domain.usecase.DeleteWallpaperDownloadUseCase
import ru.yotfr.unisoldevtest.domain.usecase.DownloadWallpaperUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetDownloadByDownloadIdUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetDownloadByWallpaperIdUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetDownloadStatusUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetWallpaperByIdUseCase
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperEvent
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperScreenEvent
import ru.yotfr.unisoldevtest.ui.wallpaper.state.WallpaperScreenState
import javax.inject.Inject

@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val getWallpaperByIdUseCase: GetWallpaperByIdUseCase,
    private val downloadWallpaperUseCase: DownloadWallpaperUseCase,
    private val checkIfFileExistsUseCase: CheckIfFileExistsUseCase,
    private val getDownloadByDownloadIdUseCase: GetDownloadByDownloadIdUseCase,
    private val getDownloadStatusUseCase: GetDownloadStatusUseCase,
    private val deleteWallpaperDownloadUseCase: DeleteWallpaperDownloadUseCase,
    private val getDownloadByWallpaperIdUseCase: GetDownloadByWallpaperIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WallpaperScreenState())
    val state = _state.asStateFlow()

    private val _event = Channel<WallpaperScreenEvent>()
    val event = _event.receiveAsFlow()

    private val id = MutableStateFlow<String?>(null)

    init {
        viewModelScope.launch {
            id.flatMapLatest { id ->
                id?.let {
                    getWallpaperByIdUseCase(it)
                } ?: flow { }
            }.collectLatest { response ->
                when (response) {
                    is MResponse.Exception -> {
                        // TODO: Error state
                    }

                    is MResponse.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is MResponse.Success -> {
                        response.data?.let { wallpaper ->
                            getDownloadStatus(wallpaper)
                            _state.update {
                                it.copy(
                                    isLoading = false,
                                    wallpaper = wallpaper
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: WallpaperEvent) {
        when (event) {
            WallpaperEvent.ChangeBarsVisibility -> {
                changeBarsVisibility()
            }

            is WallpaperEvent.EnteredScreen -> {
                setWallpaperId(event.wallpaperId)
            }

            WallpaperEvent.DownloadWallpaper -> {
                downloadWallpaper()
            }

            is WallpaperEvent.DownloadCompleted -> {
                onDownloadComplete(downloadId = event.downloadId)
            }
        }
    }

    private fun onDownloadComplete(downloadId: Long) {
        viewModelScope.launch {
            val model = getDownloadByDownloadIdUseCase(downloadId)
            if (id.value != null && model != null && id.value == model.wallpaperId) {
                viewModelScope.launch {
                    _event.send(WallpaperScreenEvent.ShowDownloadCompleteSnackbar)
                    deleteWallpaperDownloadUseCase(model)
                }
            }
        }
    }

    private fun getDownloadStatus(wallpaper: Wallpaper) {
        if (isFileExists(wallpaper)) {
            return
        }
        viewModelScope.launch {
            val wallpaperDownload = getDownloadByWallpaperIdUseCase(wallpaper.id)
            wallpaperDownload?.downloadId?.let { downloadId ->
                when (getDownloadStatusUseCase(downloadId)) {
                    DownloadStatus.FAILED -> {
                        _event.send(WallpaperScreenEvent.ShowDownloadFailedProgressSnackbar)
                    }

                    DownloadStatus.IN_PROGRESS -> {
                        _event.send(WallpaperScreenEvent.ShowDownloadFailedProgressSnackbar)
                    }

                    DownloadStatus.SUCCEED -> {
                        _event.send(WallpaperScreenEvent.ShowDownloadFailedProgressSnackbar)
                    }
                }
            }
        }
    }

    private fun isFileExists(wallpaper: Wallpaper): Boolean = checkIfFileExistsUseCase(wallpaper)

    private fun downloadWallpaper() {
        _state.value.wallpaper?.let {
            if (isFileExists(it)) {
                viewModelScope.launch {
                    _event.send(WallpaperScreenEvent.ShowFileAlreadySavedSnackbar)
                }
                return
            } else {
                viewModelScope.launch {
                    downloadWallpaperUseCase(it)
                    getDownloadStatus(it)
                }
            }
        }
    }

    private fun setWallpaperId(value: String) {
        id.value = value
    }

    private fun changeBarsVisibility() {
        _state.update {
            it.copy(
                isBarsVisible = !_state.value.isBarsVisible
            )
        }
    }

}