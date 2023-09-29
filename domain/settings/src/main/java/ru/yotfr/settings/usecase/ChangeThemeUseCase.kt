package ru.yotfr.settings.usecase

import ru.yotfr.settings.repository.SettingsRepository
import javax.inject.Inject

class ChangeThemeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(themeModel: ru.yotfr.model.ThemeModel) {
        settingsRepository.updateTheme(themeModel)
    }
}