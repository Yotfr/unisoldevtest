package ru.yotfr.unisoldevtest.ui.favoritewallpapers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.yotfr.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.usecase.ChangeWallpaperFavoriteStatusUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetFavoriteWallpapersUseCase
import ru.yotfr.unisoldevtest.ui.favoritewallpapers.event.FavoriteWallpapersEvent
import javax.inject.Inject

@HiltViewModel
class FavoriteWallpapersViewModel @Inject constructor(
    private val getFavoriteWallpapersUseCase: GetFavoriteWallpapersUseCase,
    private val changeWallpaperFavoriteStatusUseCase: ChangeWallpaperFavoriteStatusUseCase
) : ViewModel() {

    private val _favoriteWallpapers = MutableStateFlow<List<ru.yotfr.model.Wallpaper>>(emptyList())
    val favoriteWallpapers = _favoriteWallpapers.asStateFlow()

    init {
        viewModelScope.launch {
            getFavoriteWallpapersUseCase().collectLatest { wallpapers ->
                _favoriteWallpapers.value = wallpapers
            }
        }
    }

    fun onEvent(event: FavoriteWallpapersEvent) {
        when(event) {
            is FavoriteWallpapersEvent.ChangeFavorite -> {
                changeFavorite(event.wallpaper)
            }
        }
    }

    private fun changeFavorite(wallpaper: ru.yotfr.model.Wallpaper) {
        viewModelScope.launch {
            changeWallpaperFavoriteStatusUseCase(wallpaper)
        }
    }

}