package ru.yotfr.unisoldevtest.data.repository

import android.util.Log
import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import ru.yotfr.database.dao.FavoriteWallpapersDao
import ru.yotfr.network.api.WallpaperApi
import ru.yotfr.network.exception.NoConnectivityException
import ru.yotfr.unisoldevtest.data.paging.source.WallpaperPageSource
import ru.yotfr.unisoldevtest.data.mapper.mapDomain
import ru.yotfr.unisoldevtest.data.mapper.mapEntity
import ru.yotfr.unisoldevtest.data.mapper.query
import ru.yotfr.unisoldevtest.data.paging.pager.CachedWallpapersPager
import ru.yotfr.unisoldevtest.data.paging.pagingcache.WallpapersCache
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.CategoryModel
import ru.yotfr.unisoldevtest.domain.model.ErrorCause
import ru.yotfr.unisoldevtest.domain.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.model.Wallpaper
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
        category: Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<Wallpaper>> {
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
        emit(ResponseResult.Loading())
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
                ResponseResult.Success(
                    data = categories
                )
            )
        } catch (e: Exception) {
            emit(
                ResponseResult.Error(
                    cause = e.mapExceptionCause()
                )
            )
        }

    }.flowOn(Dispatchers.IO)

    override fun getWallpaperById(id: String) = flow {
        emit(ResponseResult.Loading())
        try {
            val favoriteWallpapersIds = favoriteWallpapersDao.getFavoriteWallpapersIds()
            val wallpaper = wallpaperApi.getWallpaperById(id).hits.first().mapDomain(
                isFavorite = favoriteWallpapersIds.contains(id)
            )
            emit(ResponseResult.Success(data = wallpaper))
        } catch (e: Exception) {
            emit(
                ResponseResult.Error(
                    cause = e.mapExceptionCause()
                )
            )
        }
    }.flowOn(Dispatchers.IO)

    override fun getFavoriteWallpapers(): Flow<List<Wallpaper>> =
        favoriteWallpapersDao.getFavoriteWallpapers().map { it.map { it.mapDomain() } }

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

    private fun Exception.mapExceptionCause(): ErrorCause {
        return when (this) {
            is SocketTimeoutException -> {
                ErrorCause.TimeOut
            }

            is SSLHandshakeException -> {
                ErrorCause.VPNDisabled
            }

            is ru.yotfr.network.exception.NoConnectivityException -> {
                ErrorCause.NoConnectivity
            }

            else -> {
                ErrorCause.Unknown(
                    message = this.message ?: "Something went wrong"
                )
            }
        }
    }
}

