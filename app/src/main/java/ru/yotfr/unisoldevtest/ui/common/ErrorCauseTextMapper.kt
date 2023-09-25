package ru.yotfr.unisoldevtest.ui.common

import android.content.Context
import ru.yotfr.unisoldevtest.R
import ru.yotfr.unisoldevtest.domain.model.ErrorCause

fun ErrorCause.displayText(context: Context): String {
    return when (this) {
        ErrorCause.NoConnectivity -> {
            context.getString(R.string.no_connectivity)
        }

        ErrorCause.TimeOut -> {
            context.getString(R.string.no_connectivity)
        }

        is ErrorCause.Unknown -> {
            message
        }

        ErrorCause.VPNDisabled -> {
            context.getString(R.string.time_out)
        }
    }
}