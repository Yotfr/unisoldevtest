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
import ru.yotfr.favoritewallpapers.ChangeWallpaperFavoriteStatusUseCase
import ru.yotfr.wallpaperdownloads.usecase.CheckIfFileExistsUseCase
import ru.yotfr.wallpaperdownloads.usecase.DeleteWallpaperDownloadUseCase
import ru.yotfr.wallpaperdownloads.usecase.DownloadWallpaperUseCase
import ru.yotfr.wallpaperdownloads.usecase.GetDownloadByDownloadIdUseCase
import ru.yotfr.wallpaperdownloads.usecase.GetDownloadByWallpaperIdUseCase
import ru.yotfr.wallpaperdownloads.usecase.GetDownloadStatusUseCase
import ru.yotfr.wallpaperdetails.usecase.GetWallpaperByIdUseCase
import ru.yotfr.wallpaperinstaller.usecase.InstallWallpaperUseCase
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperEvent
import ru.yotfr.unisoldevtest.ui.wallpaper.event.WallpaperScreenEvent
import ru.yotfr.unisoldevtest.ui.wallpaper.state.WallpaperScreenState
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val getWallpaperByIdUseCase: ru.yotfr.wallpaperdetails.usecase.GetWallpaperByIdUseCase,
    private val downloadWallpaperUseCase: ru.yotfr.wallpaperdownloads.usecase.DownloadWallpaperUseCase,
    private val checkIfFileExistsUseCase: ru.yotfr.wallpaperdownloads.usecase.CheckIfFileExistsUseCase,
    private val getDownloadByDownloadIdUseCase: ru.yotfr.wallpaperdownloads.usecase.GetDownloadByDownloadIdUseCase,
    private val getDownloadStatusUseCase: ru.yotfr.wallpaperdownloads.usecase.GetDownloadStatusUseCase,
    private val deleteWallpaperDownloadUseCase: ru.yotfr.wallpaperdownloads.usecase.DeleteWallpaperDownloadUseCase,
    private val getDownloadByWallpaperIdUseCase: ru.yotfr.wallpaperdownloads.usecase.GetDownloadByWallpaperIdUseCase,
    private val installWallpaperUseCase: ru.yotfr.wallpaperinstaller.usecase.InstallWallpaperUseCase,
    private val changeWallpaperFavoriteStatusUseCase: ru.yotfr.favoritewallpapers.ChangeWallpaperFavoriteStatusUseCase
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
                    is ru.yotfr.model.ResponseResult.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                        _event.send(
                            WallpaperScreenEvent.ShowErrorSnackbar(
                                errorCause = response.cause ?: ru.yotfr.model.ErrorCause.Unknown(
                                    message = "Somethings went wrong"
                                )
                            )
                        )
                    }
                    is ru.yotfr.model.ResponseResult.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }
                    is ru.yotfr.model.ResponseResult.Success -> {
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
        _state.update {
            it.copy(
                wallpaper = it.wallpaper?.copy(
                    isFavorite = !it.wallpaper.isFavorite
                )
            )
        }
    }

    private fun installWallpaper(wallpaperInstallOption: ru.yotfr.model.WallpaperInstallOption) {
        _state.value.wallpaper?.let { wallpaper ->
            viewModelScope.launch {
                installWallpaperUseCase(
                    wallpaper,
                    wallpaperInstallOption
                ).collectLatest { response ->
                    when (response) {
                        is ru.yotfr.model.ResponseResult.Error -> {
                            _event.send(
                                WallpaperScreenEvent.ShowErrorSnackbar(
                                    errorCause = response.cause ?: ru.yotfr.model.ErrorCause.Unknown(
                                        message = "Somethings went wrong"
                                    )
                                )
                            )
                        }
                        is ru.yotfr.model.ResponseResult.Loading -> {
                            _event.send(WallpaperScreenEvent.ShowInstallInProgressSnackbar)
                        }
                        is ru.yotfr.model.ResponseResult.Success -> {
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

    private fun getDownloadStatus(wallpaperDownload: ru.yotfr.model.WallpaperDownload, fromBroadcast: Boolean) {
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

    private fun getDownloadStatus(wallpaper: ru.yotfr.model.Wallpaper) {
        viewModelScope.launch {
            val wallpaperDownload = getDownloadByWallpaperIdUseCase(wallpaper.id)
            wallpaperDownload?.downloadId?.let { downloadId ->
                val downloadStatus = getDownloadStatusUseCase(downloadId)
                processDownloadStatus(downloadStatus, wallpaper, wallpaperDownload)
            }
        }
    }


    private fun processDownloadStatus(
        wallpaperStatus: ru.yotfr.model.DownloadStatus,
        wallpaper: ru.yotfr.model.Wallpaper,
        wallpaperDownload: ru.yotfr.model.WallpaperDownload,
        fromBroadcast: Boolean = false
    ) {
        viewModelScope.launch {
            when (wallpaperStatus) {
                ru.yotfr.model.DownloadStatus.FAILED -> {
                    _event.send(WallpaperScreenEvent.ShowDownloadFailedProgressSnackbar)
                    // Удаление модели загрузки из БД
                    deleteWallpaperDownloadUseCase(wallpaperDownload)
                }
                ru.yotfr.model.DownloadStatus.IN_PROGRESS -> {
                    _event.send(WallpaperScreenEvent.ShowDownloadInProgressSnackbar)
                }
                ru.yotfr.model.DownloadStatus.SUCCEED -> {
                    if (!isFileExists(wallpaper) || fromBroadcast) {
                        _event.send(WallpaperScreenEvent.ShowDownloadCompleteSnackbar)
                    }
                    // Удаление модели загрузки из БД
                    deleteWallpaperDownloadUseCase(wallpaperDownload)
                }
            }
        }
    }

    private fun isFileExists(wallpaper: ru.yotfr.model.Wallpaper): Boolean = checkIfFileExistsUseCase(wallpaper)


    private fun downloadWallpaper() {
        viewModelScope.launch {
            _state.value.wallpaper?.let {
                if (isFileExists(it)) {
                    _event.send(WallpaperScreenEvent.ShowFileAlreadySavedSnackbar)
                    return@launch
                }
                /*
                 Возвращает false в случае если недоступен WiFi и
                 разрешена загрузка изображений только по WiFi
                 */
                val downloadAllowed = downloadWallpaperUseCase(it)
                if (!downloadAllowed) {
                    _event.send(WallpaperScreenEvent.ShowDownloadOnlyByWifiAllowedSnackbar)
                }
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