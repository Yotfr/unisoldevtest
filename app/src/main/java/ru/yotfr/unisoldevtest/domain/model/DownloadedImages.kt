package ru.yotfr.unisoldevtest.domain.model

import android.net.Uri

data class DownloadedImages(
    val id: Long,
    val uri: Uri,
    val modelId: String
)
