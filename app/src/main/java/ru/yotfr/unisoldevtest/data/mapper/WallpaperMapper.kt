package ru.yotfr.unisoldevtest.data.mapper

import ru.yotfr.database.entity.WallpaperEntity
import ru.yotfr.network.model.WallpaperResponse
import ru.yotfr.unisoldevtest.domain.model.Wallpaper

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

fun ru.yotfr.database.entity.WallpaperEntity.mapDomain(): Wallpaper {
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

fun Wallpaper.mapEntity(): ru.yotfr.database.entity.WallpaperEntity {
    return ru.yotfr.database.entity.WallpaperEntity(
        id = id,
        url = url,
        previewUrl = previewUrl,
        isFavorite = isFavorite,
        previewWidth = previewWidth,
        previewHeight = previewHeight
    )
}