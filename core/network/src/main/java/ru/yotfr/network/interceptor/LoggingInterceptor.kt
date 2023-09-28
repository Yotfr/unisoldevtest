package ru.yotfr.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

/*
API KEY public
 */
internal class LoggingInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val originalHttpUrl = chain.request().url
        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("key", "39546449-afa0d5b953e7f7285eb939258").build()
        request.url(url)
        return chain.proceed(request.build())
    }
}