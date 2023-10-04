package ru.yotfr.shared.model

sealed interface ErrorCause {
    data object TimeOut : ErrorCause
    data object NoConnectivity : ErrorCause
    data class Unknown(val message: String): ErrorCause
    data object VPNDisabled : ErrorCause
}