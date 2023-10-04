package ru.yotfr.storageloader.storageloader

import kotlinx.coroutines.flow.Flow
import ru.yotfr.shared.model.DownloadedImages
import ru.yotfr.shared.model.ResponseResult

interface StorageLoader {
    suspend fun getSavedImages(): Flow<ResponseResult<List<DownloadedImages>?>>
}