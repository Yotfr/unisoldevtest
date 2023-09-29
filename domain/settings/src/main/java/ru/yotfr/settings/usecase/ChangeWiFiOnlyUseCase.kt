package ru.yotfr.settings.usecase

import ru.yotfr.settings.repository.SettingsRepository
import javax.inject.Inject

class ChangeWiFiOnlyUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(value: Boolean) {
        settingsRepository.updateAllowedOnlyWifi(value)
    }
}