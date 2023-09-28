package ru.yotfr.shared

import ru.yotfr.database.entity.WallpaperEntity
import ru.yotfr.model.Wallpaper

fun ru.yotfr.network.model.WallpaperResponse.mapDomain(isFavorite: Boolean) : Wallpaper {
    return Wallpaper(
        id = id,
        url = url,
        previewUrl = previewUrl,
        isFavorite = isFavorite,
        previewWidth = previewWidth,
        previewHeight = previewHeight,
        aspectRatio = previewWidth.toFloat() / previewHeight
    )
}

fun WallpaperEntity.mapDomain(): Wallpaper {
    return ru.yotfr.model.Wallpaper(
        id = id,
        url = url,
        previewUrl = previewUrl,
        isFavorite = isFavorite,
        previewWidth = previewWidth,
        previewHeight = previewHeight,
        aspectRatio = previewWidth.toFloat() / previewHeight
    )
}

fun Wallpaper.mapEntity(): WallpaperEntity {
    return WallpaperEntity(
        id = id,
        url = url,
        previewUrl = previewUrl,
        isFavorite = isFavorite,
        previewWidth = previewWidth,
        previewHeight = previewHeight
    )
}