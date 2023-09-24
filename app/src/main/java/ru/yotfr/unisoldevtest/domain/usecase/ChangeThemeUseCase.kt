package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.unisoldevtest.domain.model.ThemeModel
import ru.yotfr.unisoldevtest.domain.userpreference.UserPreference
import javax.inject.Inject

class ChangeThemeUseCase @Inject constructor(
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(themeModel: ThemeModel) {
        userPreference.updateTheme(themeModel)
    }
}