package ru.yotfr.wallpaperdetails.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.yotfr.database.provider.FavoriteWallpaperDatabaseProvider
import ru.yotfr.model.ResponseResult
import ru.yotfr.network.provider.WallpaperNetworkProvider
import ru.yotfr.shared.mapDomain
import ru.yotfr.shared.mapExceptionCause
import javax.inject.Inject

internal class WallpaperDetailsRepositoryImpl @Inject constructor(
    private val favoriteWallpaperDatabaseProvider: FavoriteWallpaperDatabaseProvider,
    private val wallpaperNetworkProvider: WallpaperNetworkProvider
) : WallpaperDetailsRepository {
    override fun getWallpaperById(id: String) = flow {
        emit(ResponseResult.Loading())
        try {
            favoriteWallpaperDatabaseProvider.getFavoriteWallpaperById(
                id = id
            )?.let { wallpaper ->
                emit(
                    ResponseResult.Success(
                        data = wallpaper.mapDomain()
                    )
                )
                return@flow
            }
            emit(
                ResponseResult.Success(
                    data = wallpaperNetworkProvider.getWallpaperById(
                        id = id
                    ).hits.first().mapDomain()
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
}