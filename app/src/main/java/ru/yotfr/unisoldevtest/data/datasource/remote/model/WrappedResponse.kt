package ru.yotfr.unisoldevtest.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class WrappedResponse<T>(
    @SerializedName("totalHits")
    val total: Int,
    val hits: T
)