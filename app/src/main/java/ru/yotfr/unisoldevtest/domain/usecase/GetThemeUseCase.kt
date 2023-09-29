package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.userpreference.UserPreference
import javax.inject.Inject

class GetThemeUseCase @Inject constructor(
    private val userPreference: UserPreference
) {
    operator fun invoke(): Flow<ru.yotfr.model.ThemeModel> {
        return userPreference.getTheme()
    }
}