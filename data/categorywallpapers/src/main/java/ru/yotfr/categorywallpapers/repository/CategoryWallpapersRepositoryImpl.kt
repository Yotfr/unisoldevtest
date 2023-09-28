package ru.yotfr.categorywallpapers.repository

import androidx.paging.PagingData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import ru.yotfr.categorywallpapers.paging.pager.CachedWallpapersPager
import ru.yotfr.categorywallpapers.paging.source.WallpaperPageSource
import ru.yotfr.database.provider.FavoriteWallpaperDatabaseProvider
import ru.yotfr.memorycache.pagingcache.WallpapersPagingCache
import ru.yotfr.model.Category
import ru.yotfr.model.Wallpaper
import ru.yotfr.network.provider.WallpaperNetworkProvider
import ru.yotfr.shared.query
import javax.inject.Inject

internal class CategoryWallpapersRepositoryImpl @Inject constructor(
    private val wallpaperNetworkProvider: WallpaperNetworkProvider,
    private val favoriteWallpaperDatabaseProvider: FavoriteWallpaperDatabaseProvider,
    private val wallpapersCache: WallpapersPagingCache
) : CategoryWallpapersRepository {
    override suspend fun getWallpapersByCategory(
        category: Category,
        coroutineScope: CoroutineScope
    ): Flow<PagingData<Wallpaper>> {
        val favoriteWallpaperIds = favoriteWallpaperDatabaseProvider.getFavoriteWallpapersIds()
        wallpapersCache.initializeCacheWithDbData(favoriteWallpaperIds)
        return CachedWallpapersPager(
            wallpapersCache = wallpapersCache,
            coroutineScope = coroutineScope,
            pagingFactory = WallpaperPageSource(
                wallpaperNetworkProvider = wallpaperNetworkProvider,
                category = category.query()
            )
        ).pagingDataStream
    }
}