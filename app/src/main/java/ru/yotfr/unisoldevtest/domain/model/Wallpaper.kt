package ru.yotfr.unisoldevtest.domain.model

data class Wallpaper(
    val id: String,
    val url: String,
    val previewUrl: String,
    val isFavorite: Boolean,
    val previewWidth: Int,
    val previewHeight: Int,
    val aspectRatio: Float
)
