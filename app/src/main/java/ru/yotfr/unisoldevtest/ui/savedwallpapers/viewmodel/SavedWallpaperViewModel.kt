package ru.yotfr.unisoldevtest.ui.savedwallpapers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.model.ErrorCause
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.usecase.GetDownloadedWallpapersUseCase
import ru.yotfr.unisoldevtest.ui.savedwallpapers.event.SavedWallpapersEvent
import ru.yotfr.unisoldevtest.ui.savedwallpapers.event.SavedWallpapersScreenEvent
import ru.yotfr.unisoldevtest.ui.savedwallpapers.state.SavedWallpaperScreenState
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class SavedWallpaperViewModel @Inject constructor(
    private val getDownloadedWallpapersUseCase: GetDownloadedWallpapersUseCase
) : ViewModel() {

    private val triggerRefresh = MutableStateFlow(false)

    private val _state = MutableStateFlow(SavedWallpaperScreenState())
    val state = _state.asStateFlow()

    private val _event = Channel<SavedWallpapersScreenEvent>()
    val event = _event.receiveAsFlow()

    init {
        viewModelScope.launch {
            triggerRefresh.flatMapLatest {
                getDownloadedWallpapersUseCase()
            }.collectLatest { response ->
                when (response) {
                    is ResponseResult.Error -> {
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                        _event.send(
                            SavedWallpapersScreenEvent.ShowErrorToast(
                                error = response.cause ?: ErrorCause.Unknown(
                                    message = "Somethings went wrong"
                                )
                            )
                        )
                    }

                    is ResponseResult.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is ResponseResult.Success -> {
                        _state.update {
                            it.copy(
                                isLoading = false,
                                wallpapers = response.data ?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: SavedWallpapersEvent) {
        when (event) {
            SavedWallpapersEvent.PullRefresh -> {
                refresh()
            }
        }
    }

    private fun refresh() {
        triggerRefresh.value = !triggerRefresh.value
    }
}