package ru.yotfr.unisoldevtest.ui.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.yotfr.settings.usecase.ChangeThemeUseCase
import ru.yotfr.settings.usecase.ChangeWiFiOnlyUseCase
import ru.yotfr.settings.usecase.GetThemeUseCase
import ru.yotfr.settings.usecase.GetWiFiOnlyUseCase
import ru.yotfr.unisoldevtest.ui.settings.event.SettingsEvent
import ru.yotfr.unisoldevtest.ui.settings.state.SettingsScreenState
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val changeThemeUseCase: ru.yotfr.settings.usecase.ChangeThemeUseCase,
    private val getThemeUseCase: ru.yotfr.settings.usecase.GetThemeUseCase,
    private val getWiFiOnlyUseCase: ru.yotfr.settings.usecase.GetWiFiOnlyUseCase,
    private val changeWiFiOnlyUseCase: ru.yotfr.settings.usecase.ChangeWiFiOnlyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SettingsScreenState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getThemeUseCase().combine(
                getWiFiOnlyUseCase()
            ) { theme, wifiOnly ->
                Pair(theme, wifiOnly)
            }.collectLatest { (themeModel, isWiFiOnly) ->
                _state.update {
                    it.copy(
                        currentTheme = themeModel,
                        onlyWifiEnabled = isWiFiOnly
                    )
                }
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.ThemeChanged -> {
                changeTheme(event.newTheme)
            }

            is SettingsEvent.WiFiOnlyChanged -> {
                changeWiFiOnly(event.newValue)
            }
        }
    }

    private fun changeWiFiOnly(newValue: Boolean) {
        viewModelScope.launch {
            changeWiFiOnlyUseCase(newValue)
        }
    }

    private fun changeTheme(newTheme: ru.yotfr.model.ThemeModel) {
        viewModelScope.launch {
            changeThemeUseCase(newTheme)
        }
    }
}