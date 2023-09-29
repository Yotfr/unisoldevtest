package ru.yotfr.storageloader.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.storageloader.storageloader.StorageLoader
import javax.inject.Inject

class GetDownloadedWallpapersUseCase @Inject constructor(
    private val storageLoader: StorageLoader
) {
    suspend operator fun invoke(): Flow<ru.yotfr.model.ResponseResult<List<ru.yotfr.model.DownloadedImages>?>>  {
        return storageLoader.getSavedImages()
    }
}