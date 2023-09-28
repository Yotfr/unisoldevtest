package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.DownloadedImages
import ru.yotfr.model.ResponseResult
import ru.yotfr.unisoldevtest.domain.storageloader.StorageLoader
import javax.inject.Inject

class GetDownloadedWallpapersUseCase @Inject constructor(
    private val storageLoader: StorageLoader
) {
    suspend operator fun invoke(): Flow<ru.yotfr.model.ResponseResult<List<ru.yotfr.model.DownloadedImages>?>>  {
        return storageLoader.getSavedImages()
    }
}