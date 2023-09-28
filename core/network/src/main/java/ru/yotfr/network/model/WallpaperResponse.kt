package ru.yotfr.network.model

import com.google.gson.annotations.SerializedName

data class WallpaperResponse(
    val id: String,
    @SerializedName("largeImageURL")
    val url: String,
    @SerializedName("previewURL")
    val previewUrl: String,
    val previewWidth: Int,
    val previewHeight: Int
)
