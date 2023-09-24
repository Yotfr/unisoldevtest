package ru.yotfr.unisoldevtest.domain.model

sealed class ResponseResult<T>(val data: T? = null, val cause: ExceptionCause? = null) {
    class Loading<T>(data: T? = null) : ResponseResult<T>(data, null)
    class Success<T>(data: T?) : ResponseResult<T>(data, null)
    class Exception<T>(cause: ExceptionCause) : ResponseResult<T>(null, cause)
}