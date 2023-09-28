package ru.yotfr.unisoldevtest.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.unisoldevtest.domain.repository.WallpaperRepository
import javax.inject.Inject

class WallpaperRepositoryImpl @Inject constructor(
    private val wallpaperApi: ru.yotfr.network.api.WallpaperApi,
    private val favoriteWallpapersDao: ru.yotfr.database.dao.FavoriteWallpapersDao,
    private val wallpapersCache: ru.yotfr.categorywallpapers.paging.pagingcache.WallpapersCache
) : WallpaperRepository {

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
}

