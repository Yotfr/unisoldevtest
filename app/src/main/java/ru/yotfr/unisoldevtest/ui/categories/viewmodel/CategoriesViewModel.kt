package ru.yotfr.unisoldevtest.ui.categories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.usecase.GetCategoriesUseCase
import ru.yotfr.unisoldevtest.ui.categories.state.CategoriesScreenState
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CategoriesScreenState())
    val state = _state.asStateFlow()

    private val triggerRefresh = MutableStateFlow(false)

    init {
        viewModelScope.launch {
            triggerRefresh.flatMapLatest {
                getCategoriesUseCase()
            }.collectLatest { response ->
                when (response) {
                    is ResponseResult.Exception -> {}
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
                                categories = response.data ?: emptyList()
                            )
                        }
                    }
                }
            }
        }
    }

    fun refresh() {
        triggerRefresh.value = !triggerRefresh.value
    }

}