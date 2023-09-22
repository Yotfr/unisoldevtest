package ru.yotfr.unisoldevtest.data.paging.pager

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import ru.yotfr.unisoldevtest.data.paging.pagingcache.WallpapersCache
import ru.yotfr.unisoldevtest.data.paging.source.WallpaperPageSource
import ru.yotfr.unisoldevtest.domain.model.Wallpaper

/**
 * [CachedWallpapersPager] Кастомный Pager, соединяет данные, полученные с pagingSourc'а
 * с данными в кэше
 */
class CachedWallpapersPager(
    private val wallpapersCache: WallpapersCache,
    coroutineScope: CoroutineScope,
    private val pagingFactory: WallpaperPageSource
) {

    companion object {
        const val pageSize = 50
    }

    val pagingDataStream: Flow<PagingData<Wallpaper>> =
        Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false,
                prefetchDistance = pageSize * 2
            ),
            pagingSourceFactory = {
                pagingFactory
            }
        ).flow
            .cachedIn(coroutineScope)
            .combine(
                getCachedWallpapersIsFavoriteFieldStream()
            ) { pagingData, cachedIsFavorite ->
                pagingData.map { item ->
                    mergeWithCache(item, cachedIsFavorite)
                }
            }

    private fun getCachedWallpapersIsFavoriteFieldStream(): Flow<Map<String, Boolean>> {
        return wallpapersCache.getCachedWallpapersIsFavoriteFieldStream()
    }

    private fun mergeWithCache(
        item: Wallpaper,
        cachedIsFavoriteMap: Map<String, Boolean>
    ): Wallpaper {
        val cachedIsFavoriteField = cachedIsFavoriteMap[item.id]
        return item.copy(isFavorite = cachedIsFavoriteField ?: false)
    }


}