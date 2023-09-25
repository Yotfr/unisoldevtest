package ru.yotfr.unisoldevtest.domain.usecase

import ru.yotfr.unisoldevtest.domain.userpreference.UserPreference
import javax.inject.Inject

class ChangeWiFiOnlyUseCase @Inject constructor(
    private val userPreference: UserPreference
) {
    suspend operator fun invoke(value: Boolean) {
        userPreference.updateAllowedOnlyWifi(value)
    }
}