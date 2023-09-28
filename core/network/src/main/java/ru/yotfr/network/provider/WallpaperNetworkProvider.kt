package ru.yotfr.network.provider

import ru.yotfr.network.model.WallpaperResponse
import ru.yotfr.network.model.WrappedResponse

interface WallpaperNetworkProvider {
    suspend fun getWallpapersByCategory(
        page: Int,
        perPage: Int,
        category: String
    ): WrappedResponse<List<WallpaperResponse>>

    /**
     * Для получения первой фотографии в категории
     */
    // Иного способа ограничить количество выдаваемых элементов pixabay не предоставляет
    suspend fun getCategoryPreview(
        category: String
    ): WrappedResponse<List<WallpaperResponse>>

    suspend fun getWallpaperById(
        id: String
    ): WrappedResponse<List<WallpaperResponse>>
}