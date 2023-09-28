package ru.yotfr.memorycache.pagingcache

import kotlinx.coroutines.flow.Flow

/**
 * [WallpapersPagingCache] нужен для изменения элементов pagingData (изменения статуса isFavorite)
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
interface WallpapersPagingCache {
    fun initializeCacheWithDbData(favoriteWallpaperIds: List<String>)
    fun getCachedWallpapersIsFavoriteFieldStream(): Flow<Map<String, Boolean>>
    fun updateWallpaperIsFavorite(wallpaperId: String, isFavorite: Boolean)
}