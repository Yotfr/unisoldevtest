package ru.yotfr.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.net.SocketTimeoutException

internal class TimeoutInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (isConnectionTimedOut(chain))
            throw SocketTimeoutException()
        return chain.proceed(chain.request())
    }

    private fun isConnectionTimedOut(chain: Interceptor.Chain): Boolean {
        try {
            val response = chain.proceed(chain.request())
            response.close()
        } catch (e: SocketTimeoutException) {
            return true
        }
        return false
    }
}