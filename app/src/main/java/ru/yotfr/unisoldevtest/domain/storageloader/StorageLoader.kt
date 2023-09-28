package ru.yotfr.unisoldevtest.domain.storageloader

import kotlinx.coroutines.flow.Flow
import ru.yotfr.model.DownloadedImages
import ru.yotfr.model.ResponseResult

interface StorageLoader {
    suspend fun getSavedImages(): Flow<ru.yotfr.model.ResponseResult<List<ru.yotfr.model.DownloadedImages>?>>
}