package ru.yotfr.unisoldevtest.data.datasource.remote.model

data class WrappedResponse<T>(
    val data: T,
    val meta: Meta
)