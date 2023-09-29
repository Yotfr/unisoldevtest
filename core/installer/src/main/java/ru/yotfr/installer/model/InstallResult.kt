package ru.yotfr.installer.model
sealed interface InstallResult {
    data object Installing : InstallResult
    data object Installed : InstallResult
    class Error(val error: Throwable) : InstallResult
}