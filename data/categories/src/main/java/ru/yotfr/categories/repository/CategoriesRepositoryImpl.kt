package ru.yotfr.categories.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import ru.yotfr.shared.query
import ru.yotfr.network.provider.WallpaperNetworkProvider
import ru.yotfr.shared.mapExceptionCause
import ru.yotfr.shared.model.Category
import ru.yotfr.shared.model.CategoryModel
import ru.yotfr.shared.model.ResponseResult
import javax.inject.Inject

internal class CategoriesRepositoryImpl @Inject constructor(
    private val wallpaperNetworkProvider: WallpaperNetworkProvider
) : CategoriesRepository {
    override fun getCategories() = flow {
        emit(ResponseResult.Loading())
        try {
            /*
            API Pixabay не предоставляет методов для получения доступных
            категорий и краткой информации о них
             */
            withContext(Dispatchers.Default) {

            }
            val categories = withContext(Dispatchers.Default) {
                Category.values().map {
                    val categoryResponse = withContext(Dispatchers.IO) {
                        wallpaperNetworkProvider.getCategoryPreview(it.query())
                    }
                    val wallpaper = categoryResponse.hits.first()
                    CategoryModel(
                        category = it,
                        previewUrl = wallpaper.previewUrl,
                        wallpapersCount = categoryResponse.total,
                        aspectRatio = wallpaper.previewWidth.toFloat() / wallpaper.previewHeight
                    )
                }
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
}