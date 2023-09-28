package ru.yotfr.unisoldevtest.ui.common

import android.content.Context
import ru.yotfr.unisoldevtest.R
import ru.yotfr.model.ErrorCause

fun ru.yotfr.model.ErrorCause.displayText(context: Context): String {
    return when (this) {
        ru.yotfr.model.ErrorCause.NoConnectivity -> {
            context.getString(R.string.no_connectivity)
        }

        ru.yotfr.model.ErrorCause.TimeOut -> {
            context.getString(R.string.no_connectivity)
        }

        is ru.yotfr.model.ErrorCause.Unknown -> {
            message
        }

        ru.yotfr.model.ErrorCause.VPNDisabled -> {
            context.getString(R.string.time_out)
        }
    }
}