package ru.yotfr.unisoldevtest.data.datasource.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.yotfr.unisoldevtest.data.datasource.remote.api.WallpaperApi
import ru.yotfr.unisoldevtest.data.mapper.mapDomain
import ru.yotfr.unisoldevtest.domain.model.Category
import ru.yotfr.unisoldevtest.domain.model.Wallpaper

class WallpaperPageSource(
    private val wallpaperApi: WallpaperApi,
    private val category: Category
) : PagingSource<Int, Wallpaper>() {
    override fun getRefreshKey(state: PagingState<Int, Wallpaper>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Wallpaper> {
        val page = params.key ?: 1
        val perPage = params.loadSize
        val wallpapersResponse = wallpaperApi.getWallpapersByCategory(
            page = page,
            perPage = perPage,
            category = category
        )
        val wallpapersData = wallpapersResponse.hits.map { it.mapDomain() }
        val nextKey = if (wallpapersData.size < perPage) null else page + 1
        val prevKey = if (page == 1) null else page - 1
        return LoadResult.Page(wallpapersData, prevKey, nextKey)
    }
}