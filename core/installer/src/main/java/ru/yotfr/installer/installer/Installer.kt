package ru.yotfr.installer.installer

import kotlinx.coroutines.flow.Flow
import ru.yotfr.installer.model.InstallResult

interface Installer {
    fun installHomeScreen(
        url: String
    ) : Flow<InstallResult>

    fun installLockScreen(
        url: String
    ) : Flow<InstallResult>

    fun installHomeScreenAndLockScreen(
        url: String
    ) : Flow<InstallResult>
}