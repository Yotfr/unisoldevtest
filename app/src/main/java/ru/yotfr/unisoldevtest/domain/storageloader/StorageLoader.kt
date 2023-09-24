package ru.yotfr.unisoldevtest.domain.storageloader

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.DownloadedImages
import ru.yotfr.unisoldevtest.domain.model.ResponseResult

interface StorageLoader {
    suspend fun getSavedImages(): Flow<ResponseResult<List<DownloadedImages>?>>
}