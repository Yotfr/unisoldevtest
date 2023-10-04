package ru.yotfr.categorywallpapers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import ru.yotfr.categorywallpapers.usecase.GetWallpaperByCategoryUseCase
import ru.yotfr.categorywallpapers.event.CategoryWallpapersEvent
import ru.yotfr.favoritewallpapers.usecase.ChangeWallpaperFavoriteStatusUseCase
import ru.yotfr.shared.model.Category
import ru.yotfr.shared.model.Wallpaper
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class CategoryWallpaperViewModel @Inject constructor(
    private val getWallpaperByCategoryUseCase: GetWallpaperByCategoryUseCase,
    private val changeWallpaperFavoriteStatusUseCase: ChangeWallpaperFavoriteStatusUseCase
) : ViewModel() {

    private val triggerRefresh = MutableStateFlow(false)

    private val category = MutableStateFlow<Category?>(null)

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

    private fun changeFavorite(wallpaper: Wallpaper) {
        viewModelScope.launch {
            changeWallpaperFavoriteStatusUseCase(wallpaper)
        }
    }

    private fun setCategory(value: Category) {
        category.value = value
    }

    private fun refresh() {
        triggerRefresh.value = !triggerRefresh.value
    }

}