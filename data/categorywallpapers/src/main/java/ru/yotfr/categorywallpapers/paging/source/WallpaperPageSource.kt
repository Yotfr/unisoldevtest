package ru.yotfr.categorywallpapers.paging.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.yotfr.model.Wallpaper
import ru.yotfr.network.provider.WallpaperNetworkProvider
import ru.yotfr.shared.mapDomain

internal class WallpaperPageSource(
    private val wallpaperNetworkProvider: WallpaperNetworkProvider,
    private val category: String
) : PagingSource<Int, Wallpaper>() {
    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        val page = params.key ?: 1
        val perPage = params.loadSize
        return try {
            withContext(Dispatchers.IO) {
                val wallpapersResponse = wallpaperNetworkProvider.getWallpapersByCategory(
                    page = page,
                    perPage = perPage,
                    category = category
                )
                val wallpapersData =
                    wallpapersResponse.hits.map { it.mapDomain(isFavorite = false) }
                val nextKey = if (wallpapersData.size < perPage) null else page + 1
                val prevKey = if (page == 1) null else page - 1
                LoadResult.Page(wallpapersData, prevKey, nextKey)
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}