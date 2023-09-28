package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.model.ThemeModel
import ru.yotfr.unisoldevtest.domain.userpreference.UserPreference
import javax.inject.Inject

class ChangeThemeUseCase @Inject constructor(
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(themeModel: ru.yotfr.model.ThemeModel) {
        userPreference.updateTheme(themeModel)
    }
}