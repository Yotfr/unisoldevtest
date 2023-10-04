package ru.yotfr.shared.mapper

import android.content.Context
import ru.yotfr.resources.R
import ru.yotfr.shared.model.ErrorCause

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