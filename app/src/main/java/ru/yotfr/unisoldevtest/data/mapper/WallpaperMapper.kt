package ru.yotfr.unisoldevtest.data.mapper

import ru.yotfr.database.entity.WallpaperEntity
import ru.yotfr.model.Wallpaper

fun ru.yotfr.network.model.WallpaperResponse.mapDomain(isFavorite: Boolean) : ru.yotfr.model.Wallpaper {
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

fun ru.yotfr.database.entity.WallpaperEntity.mapDomain(): ru.yotfr.model.Wallpaper {
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

fun ru.yotfr.model.Wallpaper.mapEntity(): ru.yotfr.database.entity.WallpaperEntity {
    return ru.yotfr.database.entity.WallpaperEntity(
        id = id,
        url = url,
        previewUrl = previewUrl,
        isFavorite = isFavorite,
        previewWidth = previewWidth,
        previewHeight = previewHeight
    )
}