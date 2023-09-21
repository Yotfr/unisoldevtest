package ru.yotfr.unisoldevtest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.unisoldevtest.data.datasource.remote.api.WallpaperApi
import ru.yotfr.unisoldevtest.data.datasource.remote.paging.WallpaperPageSource
import ru.yotfr.unisoldevtest.data.mapper.mapDomain
import ru.yotfr.unisoldevtest.data.mapper.query
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.domain.model.MResponse
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

    /*
    API Pixabay не предоставляет методов для получения краткой информации об имеющихся категориях.
    Возвращать информацию о всех категориях сразу слишком долго.
    С данной реализацией, элементы грузятся и добавляются в список поочередно.
     */
    override fun getCategories() = flow<MResponse<List<CategoryModel>>> {
        emit(MResponse.Loading())
        try {
            val categoriesList: ArrayList<CategoryModel> = arrayListOf()
            Category.values().forEach { category ->
                val categoryResponse = wallpaperApi.getCategoryPreview(category.query())
                val newLoadedCategory = CategoryModel(
                    category = category,
                    previewUrl = categoryResponse.hits.first().previewUrl,
                    wallpapersCount = categoryResponse.total
                )
                categoriesList.add(newLoadedCategory)
                emit(MResponse.Success(data = categoriesList))
            }
        } catch (e: Exception) {
            emit(MResponse.Exception(message = e.message))
        }

    }.flowOn(Dispatchers.IO)

    override fun getWallpaperById(id: String) = flow {
        emit(MResponse.Loading())
        try {
            val wallpaper = wallpaperApi.getWallpaperById(id).hits.first().mapDomain()
            emit(MResponse.Success(data = wallpaper))
        } catch (e: Exception) {
            emit(MResponse.Exception(message = e.message))
        }
    }.flowOn(Dispatchers.IO)
}