package ru.yotfr.shared

import ru.yotfr.network.model.NoConnectivityException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

fun Exception.mapExceptionCause(): ru.yotfr.model.ErrorCause {
    return when (this) {
        is SocketTimeoutException -> {
            ru.yotfr.model.ErrorCause.TimeOut
        }

        is SSLHandshakeException -> {
            ru.yotfr.model.ErrorCause.VPNDisabled
        }

        is NoConnectivityException -> {
            ru.yotfr.model.ErrorCause.NoConnectivity
        }

        else -> {
            ru.yotfr.model.ErrorCause.Unknown(
                message = this.message ?: "Something went wrong"
            )
        }
    }
}