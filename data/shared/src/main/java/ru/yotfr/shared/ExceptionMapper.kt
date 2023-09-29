package ru.yotfr.shared

import ru.yotfr.model.ErrorCause
import ru.yotfr.network.model.NoConnectivityException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

fun Exception.mapExceptionCause(): ErrorCause {
    return when (this) {
        is SocketTimeoutException -> {
            ErrorCause.TimeOut
        }

        is SSLHandshakeException -> {
            ErrorCause.VPNDisabled
        }

        is NoConnectivityException -> {
            ErrorCause.NoConnectivity
        }

        else -> {
            ErrorCause.Unknown(
                message = this.message ?: "Something went wrong"
            )
        }
    }
}