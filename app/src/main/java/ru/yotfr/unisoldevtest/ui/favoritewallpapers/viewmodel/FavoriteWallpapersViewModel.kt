package ru.yotfr.unisoldevtest.ui.favoritewallpapers.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.usecase.ChangeWallpaperFavoriteStatusUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetFavoriteWallpapersUseCase
import javax.inject.Inject

@HiltViewModel
class FavoriteWallpapersViewModel @Inject constructor(
    private val getFavoriteWallpapersUseCase: GetFavoriteWallpapersUseCase,
    private val changeWallpaperFavoriteStatusUseCase: ChangeWallpaperFavoriteStatusUseCase
) : ViewModel() {

    private val _favoriteWallpapers = MutableStateFlow<List<Wallpaper>>(emptyList())
    val favoriteWallpapers = _favoriteWallpapers.asStateFlow()

    init {
        viewModelScope.launch {
            getFavoriteWallpapersUseCase().collectLatest { wallpapers ->
                _favoriteWallpapers.value = wallpapers
            }
        }
    }

    fun changeFavorite(wallpaper: Wallpaper) {
        viewModelScope.launch {
            changeWallpaperFavoriteStatusUseCase(wallpaper)
        }
    }

}