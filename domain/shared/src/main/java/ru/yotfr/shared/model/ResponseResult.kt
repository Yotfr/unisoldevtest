package ru.yotfr.shared.model

sealed class ResponseResult<T>(val data: T? = null, val cause: ErrorCause? = null) {
    class Loading<T>(data: T? = null) : ResponseResult<T>(data, null)
    class Success<T>(data: T?) : ResponseResult<T>(data, null)
    class Error<T>(cause: ErrorCause) : ResponseResult<T>(null, cause)
}