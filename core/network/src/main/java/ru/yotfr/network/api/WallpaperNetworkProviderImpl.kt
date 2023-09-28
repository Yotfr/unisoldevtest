package ru.yotfr.network.api

import ru.yotfr.network.model.WallpaperResponse
import ru.yotfr.network.model.WrappedResponse
import javax.inject.Inject

internal class WallpaperNetworkProviderImpl @Inject constructor(
    private val wallpaperApi: WallpaperApi
): WallpaperNetworkProvider {
    override suspend fun getWallpapersByCategory(
        page: Int,
        perPage: Int,
        category: String
    ): WrappedResponse<List<WallpaperResponse>> {
        return wallpaperApi.getWallpapersByCategory(
            page = page,
            perPage = perPage,
            category = category
        )
    }

    override suspend fun getCategoryPreview(category: String): WrappedResponse<List<WallpaperResponse>> {
        return wallpaperApi.getCategoryPreview(category = category)
    }

    override suspend fun getWallpaperById(id: String): WrappedResponse<List<WallpaperResponse>> {
        return wallpaperApi.getWallpaperById(id = id)
    }
}