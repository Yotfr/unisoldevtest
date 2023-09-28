package ru.yotfr.categorywallpapers.paging.pagingcache

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * [WallpapersCacheImpl] нужен для изменения элементов pagingData (изменения статуса isFavorite)
 *
 * [initializeCacheWithDbData] добавляет в кэш все избранные обои из базы данных Room
 * [getCachedWallpapersIsFavoriteFieldStream] отдает кэш избранных обоев Map<Id обоев, IsFavorite статус>
 * [updateWallpaperIsFavorite] добавляет в кэш новые избранные обои
 *
 * Данный интерфейс необходим, так как после изменения статуса на экране,
 * и последующего добавления нового элемента в БД, pagingData не обновляется, вызывать refresh на Pager'e и обновлять
 * все пагинируемые данные после каждого изменения элемента, либо подтягивать флоу с БД со списком id избранных обоев
 * до экрана и узнавать наличие каждого элемента  слишком дорого
 */
internal class WallpapersCacheImpl : WallpapersCache {

    private val cachedWallpapersIsFavoriteFieldStream =
        MutableStateFlow<Map<String, Boolean>>(emptyMap())

    override fun initializeCacheWithDbData(favoriteWallpaperIds: List<String>) {
        favoriteWallpaperIds.forEach {
            cachedWallpapersIsFavoriteFieldStream.value += it to true
        }
    }

    override fun getCachedWallpapersIsFavoriteFieldStream(): Flow<Map<String, Boolean>> {
        return cachedWallpapersIsFavoriteFieldStream
    }

    override fun updateWallpaperIsFavorite(wallpaperId: String, isFavorite: Boolean) {
        cachedWallpapersIsFavoriteFieldStream.value += wallpaperId to isFavorite
    }
}