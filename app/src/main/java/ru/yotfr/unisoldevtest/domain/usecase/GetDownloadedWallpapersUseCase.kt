package ru.yotfr.unisoldevtest.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.DownloadedImages
import ru.yotfr.unisoldevtest.domain.model.MResponse
import ru.yotfr.unisoldevtest.domain.storageloader.StorageLoader
import javax.inject.Inject

class GetDownloadedWallpapersUseCase @Inject constructor(
    private val storageLoader: StorageLoader
) {
    suspend operator fun invoke(): Flow<MResponse<List<DownloadedImages>?>>  {
        return storageLoader.getSavedImages()
    }
}