package ru.yotfr.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.yotfr.shared.navigation.RootScreens

fun NavGraphBuilder.settingsScreen(
    navigateBack: () -> Unit
) {
    composable(
        route = RootScreens.Settings.route
    ) {
        ru.yotfr.settings.screen.SettingsScreen(
            navigateBack = navigateBack
        )
    }
}