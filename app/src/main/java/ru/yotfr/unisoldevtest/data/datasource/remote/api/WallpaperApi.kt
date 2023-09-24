package ru.yotfr.unisoldevtest.data.datasource.remote.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.yotfr.unisoldevtest.data.datasource.remote.model.WallpaperResponse
import ru.yotfr.unisoldevtest.data.datasource.remote.model.WrappedResponse

interface WallpaperApi {
    @GET(".")
    suspend fun getWallpapersByCategory(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
        @Query("category") category: String,
        @Query("safesearch") safesearch: String = "true",
        @Query("orientation") orientation: String = "vertical"
    ): WrappedResponse<List<WallpaperResponse>>

    /**
     * Для получения первой фотографии в категории
     */
    // Иного способа ограничить количество выдаваемых элементов pixabay не предоставляет
    @GET("?page=1&per_page=3")
    suspend fun getCategoryPreview(
        @Query("category") category: String,
        @Query("safesearch") safesearch: String = "true",
        @Query("orientation") orientation: String = "vertical"
    ): WrappedResponse<List<WallpaperResponse>>

    @GET(".")
    suspend fun getWallpaperById(
        @Query("id") id: String,
    ): WrappedResponse<List<WallpaperResponse>>
}