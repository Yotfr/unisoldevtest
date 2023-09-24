package ru.yotfr.unisoldevtest.domain.model

sealed interface ExceptionCause {
    data object TimeOut : ExceptionCause
    data object NoConnectivity : ExceptionCause
    data class Unknown(val message: String): ExceptionCause
}