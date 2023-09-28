package ru.yotfr.storageloader.storageloader

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.DownloadedImages
import ru.yotfr.model.ResponseResult

interface StorageLoader {
    suspend fun getSavedImages(): Flow<ResponseResult<List<DownloadedImages>?>>
}