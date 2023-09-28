package ru.yotfr.network.model

import com.google.gson.annotations.SerializedName

data class WrappedResponse<T>(
    @SerializedName("totalHits")
    val total: Int,
    val hits: T
)