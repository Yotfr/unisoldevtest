package ru.yotfr.unisoldevtest.ui.wallpaper.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.model.MResponse
import ru.yotfr.unisoldevtest.domain.usecase.DownloadWallpaperUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetWallpaperByIdUseCase
import ru.yotfr.unisoldevtest.ui.wallpaper.state.WallpaperScreenState
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class WallpaperViewModel @Inject constructor(
    private val getWallpaperByIdUseCase: GetWallpaperByIdUseCase,
    private val downloadWallpaperUseCase: DownloadWallpaperUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(WallpaperScreenState())
    val state = _state.asStateFlow()

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
                        _state.update {
                            it.copy(
                                isLoading = false,
                                wallpaper = response.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun downloadWallpaper() {
        _state.value.wallpaper?.let {
            viewModelScope.launch {
                downloadWallpaperUseCase(it)
            }
        }
    }

    fun changeId(value: String) {
        id.value = value
    }

    fun changeButtonsRowVisibility() {
        _state.update {
            it.copy(
                isBarsVisible = !_state.value.isBarsVisible
            )
        }
    }

}