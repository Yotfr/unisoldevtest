package ru.yotfr.unisoldevtest.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.unisoldevtest.domain.model.ThemeModel
import ru.yotfr.unisoldevtest.domain.usecase.ChangeThemeUseCase
import ru.yotfr.unisoldevtest.domain.usecase.GetThemeUseCase
import ru.yotfr.unisoldevtest.ui.settings.event.SettingsEvent
import ru.yotfr.unisoldevtest.ui.settings.state.SettingsScreenState
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val changeThemeUseCase: ChangeThemeUseCase,
    private val getThemeUseCase: GetThemeUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getThemeUseCase().collectLatest { themeModel ->
                _state.update {
                    it.copy(
                        currentTheme = themeModel
                    )
                }
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when(event) {
            is SettingsEvent.ThemeChanged -> {
                changeTheme(event.newTheme)
            }
        }
    }

    private fun changeTheme(newTheme: ThemeModel) {
        viewModelScope.launch {
            changeThemeUseCase(newTheme)
        }
    }
}