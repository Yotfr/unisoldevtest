package ru.yotfr.unisoldevtest.data.repository

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.yotfr.network.exception.NoConnectivityException
import ru.yotfr.unisoldevtest.data.paging.source.WallpaperPageSource
import ru.yotfr.unisoldevtest.data.mapper.mapDomain
import ru.yotfr.unisoldevtest.data.mapper.mapEntity
import ru.yotfr.categories.query
import ru.yotfr.unisoldevtest.data.paging.pager.CachedWallpapersPager
import ru.yotfr.unisoldevtest.data.paging.pagingcache.WallpapersCache
import ru.yotfr.model.Category
import ru.yotfr.model.CategoryModel
import ru.yotfr.model.ErrorCause
import ru.yotfr.model.Wallpaper
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import java.net.SocketTimeoutException
import javax.inject.Inject
import javax.net.ssl.SSLHandshakeException

class WallpaperRepositoryImpl @Inject constructor(
    private val wallpaperApi: ru.yotfr.network.api.WallpaperApi,
    private val favoriteWallpapersDao: ru.yotfr.database.dao.FavoriteWallpapersDao,
    private val wallpapersCache: WallpapersCache
) : WallpaperRepository {
    override suspend fun getWallpapersByCategory(
        category: ru.yotfr.model.Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<ru.yotfr.model.Wallpaper>> {
        // Добавление избранных обоев из БД в кэш
        val favoriteWallpaperIds = favoriteWallpapersDao.getFavoriteWallpapersIds()
        wallpapersCache.initializeCacheWithDbData(favoriteWallpaperIds)
        return CachedWallpapersPager(
            wallpapersCache = wallpapersCache,
            coroutineScope = coroutineScope,
            pagingFactory = WallpaperPageSource(
                wallpaperApi = wallpaperApi,
                category = category.query()
            )
        ).pagingDataStream
    }


    override fun getCategories() = flow {
        emit(ru.yotfr.model.ResponseResult.Loading())
        try {
            /*
            API Pixabay не предоставляет методов для получения доступных
            категорий и краткой информации о них
             */
            val categories = ru.yotfr.model.Category.values().map {
                val categoryResponse = wallpaperApi.getCategoryPreview(it.query())
                val wallpaper = categoryResponse.hits.first()
                ru.yotfr.model.CategoryModel(
                    category = it,
                    previewUrl = wallpaper.previewUrl,
                    wallpapersCount = categoryResponse.total,
                    aspectRatio = wallpaper.previewWidth.toFloat() / wallpaper.previewHeight
                )
            }
            emit(
                ru.yotfr.model.ResponseResult.Success(
                    data = categories
                )
            )
        } catch (e: Exception) {
            emit(
                ru.yotfr.model.ResponseResult.Error(
                    cause = e.mapExceptionCause()
                )
            )
        }

    }.flowOn(Dispatchers.IO)

    override fun getWallpaperById(id: String) = flow {
        emit(ru.yotfr.model.ResponseResult.Loading())
        try {
            val favoriteWallpapersIds = favoriteWallpapersDao.getFavoriteWallpapersIds()
            val wallpaper = wallpaperApi.getWallpaperById(id).hits.first().mapDomain(
                isFavorite = favoriteWallpapersIds.contains(id)
            )
            emit(ru.yotfr.model.ResponseResult.Success(data = wallpaper))
        } catch (e: Exception) {
            emit(
                ru.yotfr.model.ResponseResult.Error(
                    cause = e.mapExceptionCause()
                )
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun getFavoriteWallpapers(): Flow<List<ru.yotfr.model.Wallpaper>> =
        favoriteWallpapersDao.getFavoriteWallpapers().map { it.map { it.mapDomain() } }

    override suspend fun changeWallpaperFavoriteStatus(wallpaper: ru.yotfr.model.Wallpaper) {
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

