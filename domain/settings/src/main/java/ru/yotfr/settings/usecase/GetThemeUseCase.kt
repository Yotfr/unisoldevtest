package ru.yotfr.settings.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.settings.repository.SettingsRepository
import ru.yotfr.shared.model.ThemeModel
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke(): Flow<ThemeModel> {
        return settingsRepository.getTheme()
    }
}