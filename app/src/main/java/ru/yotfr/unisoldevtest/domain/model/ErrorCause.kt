package ru.yotfr.unisoldevtest.domain.model

sealed interface ErrorCause {
    data object TimeOut : ErrorCause
    data object NoConnectivity : ErrorCause
    data class Unknown(val message: String): ErrorCause
    data object VPNDisabled : ErrorCause
}