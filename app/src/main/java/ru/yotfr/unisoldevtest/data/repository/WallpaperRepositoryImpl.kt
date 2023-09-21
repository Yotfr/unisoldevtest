package ru.yotfr.unisoldevtest.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.unisoldevtest.data.datasource.local.dao.FavoriteWallpapersDao
import ru.yotfr.unisoldevtest.data.datasource.remote.api.WallpaperApi
import ru.yotfr.unisoldevtest.data.datasource.remote.paging.WallpaperPageSource
import ru.yotfr.unisoldevtest.data.mapper.mapDomain
import ru.yotfr.unisoldevtest.data.mapper.mapEntity
import ru.yotfr.unisoldevtest.data.mapper.query
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.domain.model.MResponse
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    private val wallpaperApi: WallpaperApi,
    private val favoriteWallpapersDao: FavoriteWallpapersDao
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
     */
    override fun getCategories() = flow {
        emit(MResponse.Loading())
        try {
            val categories = Category.values().map {
                val categoryResponse = wallpaperApi.getCategoryPreview(it.query())
                CategoryModel(
                    category = it,
                    previewUrl = categoryResponse.hits.first().previewUrl,
                    wallpapersCount = categoryResponse.total
                )
            }
            emit(
                MResponse.Success(
                    data = categories
                )
            )
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

    override suspend fun changeWallpaperFavoriteStatus(wallpaper: Wallpaper) {
        if (wallpaper.isFavorite) {
            favoriteWallpapersDao.deleteWallpaper(wallpaper.mapEntity())
        } else {
            favoriteWallpapersDao.insertWallpaper(wallpaper.mapEntity())
        }
    }
}