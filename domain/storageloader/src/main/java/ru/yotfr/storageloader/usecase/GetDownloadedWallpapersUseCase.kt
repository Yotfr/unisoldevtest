package ru.yotfr.storageloader.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.shared.model.DownloadedImages
import ru.yotfr.shared.model.ResponseResult
import ru.yotfr.storageloader.storageloader.StorageLoader
import javax.inject.Inject

class GetDownloadedWallpapersUseCase @Inject constructor(
    private val storageLoader: StorageLoader
) {
    suspend operator fun invoke(): Flow<ResponseResult<List<DownloadedImages>?>>  {
        return storageLoader.getSavedImages()
    }
}