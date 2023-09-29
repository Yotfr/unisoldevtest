package ru.yotfr.unisoldevtest.ui.categorywallpapers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.usecase.ChangeWallpaperFavoriteStatusUseCase
import ru.yotfr.categorywallpapers.usecase.GetWallpaperByCategoryUseCase
import ru.yotfr.unisoldevtest.ui.categorywallpapers.event.CategoryWallpapersEvent
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CategoryWallpaperViewModel @Inject constructor(
    private val getWallpaperByCategoryUseCase: ru.yotfr.categorywallpapers.usecase.GetWallpaperByCategoryUseCase,
    private val changeWallpaperFavoriteStatusUseCase: ChangeWallpaperFavoriteStatusUseCase
) : ViewModel() {

    private val triggerRefresh = MutableStateFlow(false)

    private val category = MutableStateFlow<ru.yotfr.model.Category?>(null)

    val wallpapers = combine(
        category, triggerRefresh
    ) { category, refresh ->
        Pair(category, refresh)
    }.flatMapLatest { (category, _) ->
        category?.let {
            getWallpaperByCategoryUseCase(it, viewModelScope)
        } ?: flow { }
    }

    fun onEvent(event: CategoryWallpapersEvent) {
        when(event) {
            is CategoryWallpapersEvent.ChangeFavorite -> {
                changeFavorite(event.wallpaper)
            }
            CategoryWallpapersEvent.PullRefresh -> {
                refresh()
            }
            is CategoryWallpapersEvent.SetCategory -> {
                setCategory(event.category)
            }
        }
    }

    private fun changeFavorite(wallpaper: ru.yotfr.model.Wallpaper) {
        viewModelScope.launch {
            changeWallpaperFavoriteStatusUseCase(wallpaper)
        }
    }

    private fun setCategory(value: ru.yotfr.model.Category) {
        category.value = value
    }

    private fun refresh() {
        triggerRefresh.value = !triggerRefresh.value
    }

}