package ru.yotfr.unisoldevtest.domain.model

sealed class MResponse<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(data: T? = null) : MResponse<T>(data, null)
    class Success<T>(data: T?) : MResponse<T>(data, null)
    class Exception<T>(message: String?) : MResponse<T>(null, message)
}