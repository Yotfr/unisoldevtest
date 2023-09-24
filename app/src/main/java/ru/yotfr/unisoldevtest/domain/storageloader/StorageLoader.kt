package ru.yotfr.unisoldevtest.domain.storageloader

import kotlinx.coroutines.flow.Flow
import ru.yotfr.unisoldevtest.domain.model.DownloadedImages
import ru.yotfr.unisoldevtest.domain.model.MResponse

interface StorageLoader {
    suspend fun getSavedImages(): Flow<MResponse<List<DownloadedImages>?>>
}