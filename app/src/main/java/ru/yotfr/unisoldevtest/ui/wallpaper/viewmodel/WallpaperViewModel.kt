package ru.yotfr.unisoldevtest.ui.wallpaper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.model.WallpaperDownload
import ru.yotfr.unisoldevtest.domain.model.WallpaperInstallOption
import ru.yotfr.unisoldevtest.domain.usecase.ChangeWallpaperFavoriteStatusUseCase
import ru.yotfr.unisoldevtest.domain.usecase.CheckIfFileExistsUseCase
import ru.yotfr.unisoldevtest.domain.usecase.DeleteWallpaperDownloadUseCase
import ru.yotfr.unisoldevtest.domain.usecase.DownloadWallpaperUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetDownloadByDownloadIdUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetDownloadByWallpaperIdUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetDownloadStatusUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetWallpaperByIdUseCase
import ru.yotfr.unisoldevtest.domain.usecase.InstallWallpaperUseCase
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperEvent
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperScreenEvent
import ru.yotfr.unisoldevtest.ui.wallpaper.state.WallpaperScreenState
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val getWallpaperByIdUseCase: GetWallpaperByIdUseCase,
    private val downloadWallpaperUseCase: DownloadWallpaperUseCase,
    private val checkIfFileExistsUseCase: CheckIfFileExistsUseCase,
    private val getDownloadByDownloadIdUseCase: GetDownloadByDownloadIdUseCase,
    private val getDownloadStatusUseCase: GetDownloadStatusUseCase,
    private val deleteWallpaperDownloadUseCase: DeleteWallpaperDownloadUseCase,
    private val getDownloadByWallpaperIdUseCase: GetDownloadByWallpaperIdUseCase,
    private val installWallpaperUseCase: InstallWallpaperUseCase,
    private val changeWallpaperFavoriteStatusUseCase: ChangeWallpaperFavoriteStatusUseCase
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
                    is ResponseResult.Exception -> {
                        // TODO: Error state
                    }

                    is ResponseResult.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is ResponseResult.Success -> {
                        response.data?.let { wallpaper ->
                            /*
                             Для случаев долгой загрузки, для того чтобы уведомить пользовтеля
                             о статусе загрузки при возвращении на экран
                             */
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

            is WallpaperEvent.InstallWallpaper -> {
                installWallpaper(event.wallpaperInstallOption)
            }

            WallpaperEvent.ChangeFavoriteStatus -> {
                changeFavoriteStatusUseCase()
            }
        }
    }

    private fun changeFavoriteStatusUseCase() {
        _state.value.wallpaper?.let { wallpaper ->
            viewModelScope.launch {
                changeWallpaperFavoriteStatusUseCase(
                    wallpaper = wallpaper
                )
            }
        }
    }

    private fun installWallpaper(wallpaperInstallOption: WallpaperInstallOption) {
        _state.value.wallpaper?.let { wallpaper ->
            viewModelScope.launch {
                installWallpaperUseCase(
                    wallpaper,
                    wallpaperInstallOption
                ).collectLatest { response ->
                    when(response) {
                        is ResponseResult.Exception -> {

                        }
                        is ResponseResult.Loading -> {
                            _event.send(WallpaperScreenEvent.ShowInstallInProgressSnackbar)
                        }
                        is ResponseResult.Success -> {
                            _event.send(WallpaperScreenEvent.ShowInstallCompletedSnackbar)
                        }
                    }
                }
            }
        }
    }

    private fun onDownloadComplete(downloadId: Long) {
        viewModelScope.launch {
            val model = getDownloadByDownloadIdUseCase(downloadId)
            if (id.value != null && model != null && id.value == model.wallpaperId) {
                // После получения бродкаста о звершении загрузки проверяет статус загрузки
                getDownloadStatus(model, true)
            }
        }
    }

    private fun getDownloadStatus(wallpaperDownload: WallpaperDownload, fromBroadcast: Boolean) {
        viewModelScope.launch {
            wallpaperDownload.downloadId.let { downloadId ->
                _state.value.wallpaper?.let { wallpaper ->
                    val downloadStatus = getDownloadStatusUseCase(downloadId)
                    processDownloadStatus(
                        downloadStatus,
                        wallpaper,
                        wallpaperDownload,
                        fromBroadcast
                    )
                }
            }
        }
    }

    private fun getDownloadStatus(wallpaper: Wallpaper) {
        viewModelScope.launch {
            val wallpaperDownload = getDownloadByWallpaperIdUseCase(wallpaper.id)
            wallpaperDownload?.downloadId?.let { downloadId ->
                val downloadStatus = getDownloadStatusUseCase(downloadId)
                processDownloadStatus(downloadStatus, wallpaper, wallpaperDownload)
            }
        }
    }


    private fun processDownloadStatus(
        wallpaperStatus: DownloadStatus,
        wallpaper: Wallpaper,
        wallpaperDownload: WallpaperDownload,
        fromBroadcast: Boolean = false
    ) {
        viewModelScope.launch {
            when (wallpaperStatus) {
                DownloadStatus.FAILED -> {
                    _event.send(WallpaperScreenEvent.ShowDownloadFailedProgressSnackbar)
                    // Удаление модели загрузки из БД
                    deleteWallpaperDownloadUseCase(wallpaperDownload)
                }

                DownloadStatus.IN_PROGRESS -> {
                    _event.send(WallpaperScreenEvent.ShowDownloadInProgressSnackbar)
                }

                DownloadStatus.SUCCEED -> {
                    if (!isFileExists(wallpaper) || fromBroadcast) {
                        _event.send(WallpaperScreenEvent.ShowDownloadCompleteSnackbar)
                    }
                    // Удаление модели загрузки из БД
                    deleteWallpaperDownloadUseCase(wallpaperDownload)
                }
            }
        }
    }

    private fun isFileExists(wallpaper: Wallpaper): Boolean = checkIfFileExistsUseCase(wallpaper)


    private fun downloadWallpaper() {
        viewModelScope.launch {
            _state.value.wallpaper?.let {
                if (isFileExists(it)) {
                    _event.send(WallpaperScreenEvent.ShowFileAlreadySavedSnackbar)
                    return@launch
                }
                downloadWallpaperUseCase(it)
                getDownloadStatus(it)
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