package ru.yotfr.unisoldevtest.ui.savedwallpapers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.usecase.GetDownloadedWallpapersUseCase
import ru.yotfr.unisoldevtest.ui.savedwallpapers.state.SavedWallpaperScreenState
import javax.inject.Inject

@HiltViewModel
class SavedWallpaperViewModel @Inject constructor(
    private val getDownloadedWallpapersUseCase: GetDownloadedWallpapersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SavedWallpaperScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getDownloadedWallpapersUseCase().collectLatest { response ->
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
                        _state.update {
                            it.copy(
                                isLoading = true,
                                wallpapers = response.data ?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }
}