package ru.yotfr.unisoldevtest.data.datasource.remote.model

data class WrappedResponse<T>(
    val total: Int,
    val hits: T
)