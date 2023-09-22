package ru.yotfr.unisoldevtest.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.unisoldevtest.data.datasource.local.dao.FavoriteWallpapersDao
import ru.yotfr.unisoldevtest.data.datasource.remote.api.WallpaperApi
import ru.yotfr.unisoldevtest.data.paging.source.WallpaperPageSource
import ru.yotfr.unisoldevtest.data.mapper.mapDomain
import ru.yotfr.unisoldevtest.data.mapper.mapEntity
import ru.yotfr.unisoldevtest.data.mapper.query
import ru.yotfr.unisoldevtest.data.paging.pager.CachedWallpapersPager
import ru.yotfr.unisoldevtest.data.paging.pagingcache.WallpapersCache
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.domain.model.MResponse
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject
class WallpaperRepositoryImpl @Inject constructor(
    private val wallpaperApi: WallpaperApi,
    private val favoriteWallpapersDao: FavoriteWallpapersDao,
    private val wallpapersCache: WallpapersCache
) : WallpaperRepository {
    override suspend fun getWallpapersByCategory(
        category: Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<Wallpaper>> {
        // Добавление избранныз обоев из БД в кэш
        val favoriteWallpaperIds = favoriteWallpapersDao.getFavoriteWallpapersIds()
        wallpapersCache.initializeCacheWithDbData(favoriteWallpaperIds)
        return CachedWallpapersPager(
            wallpapersCache = wallpapersCache,
            coroutineScope = coroutineScope,
            pagingFactory = WallpaperPageSource(
                wallpaperApi = wallpaperApi,
                category = category.query(),
                favoriteWallpapersDao = favoriteWallpapersDao
            )
        ).pagingDataStream
    }


    override fun getCategories() = flow {
        emit(MResponse.Loading())
        try {
            /*
            API Pixabay не предоставляет методов для получения доступных
            категорий и краткой информации о них
             */
            val categories = Category.values().map {
                val categoryResponse = wallpaperApi.getCategoryPreview(it.query())
                val wallpaper = categoryResponse.hits.first()
                CategoryModel(
                    category = it,
                    previewUrl = wallpaper.previewUrl,
                    wallpapersCount = categoryResponse.total,
                    aspectRatio = wallpaper.previewWidth.toFloat() / wallpaper.previewHeight
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
            val favoriteWallpapersIds = favoriteWallpapersDao.getFavoriteWallpapersIds()
            val wallpaper = wallpaperApi.getWallpaperById(id).hits.first().mapDomain(
                isFavorite = favoriteWallpapersIds.contains(id)
            )
            emit(MResponse.Success(data = wallpaper))
        } catch (e: Exception) {
            emit(MResponse.Exception(message = e.message))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun changeWallpaperFavoriteStatus(wallpaper: Wallpaper) {
        wallpapersCache.updateWallpaperIsFavorite(
            wallpaperId = wallpaper.id,
            isFavorite = !wallpaper.isFavorite
        )
        val changedWallpaper = wallpaper.copy(isFavorite = true)
        if (wallpaper.isFavorite) {
            favoriteWallpapersDao.deleteWallpaper(wallpaper.mapEntity())
        } else {
            favoriteWallpapersDao.upsertWallpaper(changedWallpaper.mapEntity())
        }
    }
}

