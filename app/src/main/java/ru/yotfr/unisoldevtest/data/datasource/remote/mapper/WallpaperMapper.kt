package ru.yotfr.unisoldevtest.data.datasource.remote.mapper

import ru.yotfr.unisoldevtest.data.datasource.remote.model.WallpaperResponse
import ru.yotfr.unisoldevtest.domain.model.Wallpaper

fun WallpaperResponse.mapDomain() : Wallpaper {
    return Wallpaper(
        id = id,
        url = url,
        fileSize = fileSize,
        fileType = fileType,
        path = path
    )
}