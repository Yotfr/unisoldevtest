package ru.yotfr.settings.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.settings.repository.SettingsRepository
import javax.inject.Inject

class GetWiFiOnlyUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return settingsRepository.getAllowedOnlyWifi()
    }
}