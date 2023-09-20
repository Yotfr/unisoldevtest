package ru.yotfr.unisoldevtest.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class WallpaperResponse(
    val id: String,
    @SerializedName("largeImageURL")
    val url: String,
    @SerializedName("previewURL")
    val previewUrl: String
)
