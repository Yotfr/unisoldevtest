package ru.yotfr.unisoldevtest.ui.categories.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.model.MResponse
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
            }.collect { response ->
                when (response) {
                    is MResponse.Exception -> {}
                    is MResponse.Loading -> {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    }

                    is MResponse.Success -> {
                        /*
                        Необходим для вызова рекомпозиции в Compose скрине при
                        изменении элементов листа categories.
                        (Для реализации поэтапной загрузки информации о категории и
                        добавлении этой категории в список)
                         */
                        _state.update {
                            it.copy(
                                isLoading = false,
                                categories = response.data ?: emptyList(),
                                trigger = !it.trigger
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