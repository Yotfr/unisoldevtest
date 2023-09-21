package ru.yotfr.unisoldevtest.data.mapper

import ru.yotfr.unisoldevtest.data.datasource.local.entity.WallpaperEntity
import ru.yotfr.unisoldevtest.data.datasource.remote.model.WallpaperResponse
import ru.yotfr.unisoldevtest.domain.model.Wallpaper

fun WallpaperResponse.mapDomain() : Wallpaper {
    return Wallpaper(
        id = id,
        url = url,
        previewUrl = previewUrl,
        isFavorite = false
    )
}

fun WallpaperEntity.mapDomain(): Wallpaper {
    return Wallpaper(
        id = id,
        url = url,
        previewUrl = previewUrl,
        isFavorite = isFavorite
    )
}

fun Wallpaper.mapEntity(): WallpaperEntity {
    return WallpaperEntity(
        id = id,
        url = url,
        previewUrl = previewUrl,
        isFavorite = isFavorite
    )
}