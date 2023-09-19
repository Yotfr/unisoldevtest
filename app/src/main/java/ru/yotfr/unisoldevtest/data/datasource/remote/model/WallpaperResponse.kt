package ru.yotfr.unisoldevtest.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class WallpaperResponse(
    val id: String,
    val url: String,
    @SerializedName("file_size")
    val fileSize: Int,
    @SerializedName("file_type")
    val fileType: String,
    val path: String
)
