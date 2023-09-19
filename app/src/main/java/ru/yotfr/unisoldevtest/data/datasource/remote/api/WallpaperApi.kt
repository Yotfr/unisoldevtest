package ru.yotfr.unisoldevtest.data.datasource.remote.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import ru.yotfr.unisoldevtest.data.datasource.remote.model.WallpaperResponse
import ru.yotfr.unisoldevtest.data.datasource.remote.model.WrappedResponse

interface WallpaperApi {
    @GET("v1/search")
    suspend fun getWallpapers(
        @Query("page") page: Int
    ): WrappedResponse<List<WallpaperResponse>>
}

fun WallpaperApi() : WallpaperApi {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://wallhaven.cc/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(WallpaperApi::class.java)
}