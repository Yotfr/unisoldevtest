package ru.yotfr.unisoldevtest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.data.datasource.remote.api.WallpaperApi
import ru.yotfr.unisoldevtest.data.datasource.remote.paging.WallpaperPageSource
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    private val wallpaperApi: WallpaperApi
) : WallpaperRepository {
    override fun getWallpapersByCategory(category: Category): Flow<PagingData<Wallpaper>> =
        Pager(
            config = PagingConfig(
                pageSize = 50
            ),
            pagingSourceFactory = {
                WallpaperPageSource(
                    wallpaperApi = wallpaperApi,
                    category = category
                )
            }
        ).flow

    override suspend fun getCategories(): List<CategoryModel> {
        return Category.values().map { category ->
            val categoryResponse = wallpaperApi.getCategoryPreview(category)
            CategoryModel(
                category = category,
                previewUrl = categoryResponse.hits.first().previewUrl,
                wallpapersCount = categoryResponse.total
            )
        }
    }
}