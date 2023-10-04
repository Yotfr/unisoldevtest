package ru.yotfr.settings.usecase

import ru.yotfr.settings.repository.SettingsRepository
import ru.yotfr.shared.model.ThemeModel
import javax.inject.Inject

class ChangeThemeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(themeModel: ThemeModel) {
        settingsRepository.updateTheme(themeModel)
    }
}