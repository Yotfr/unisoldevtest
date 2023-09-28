package ru.yotfr.unisoldevtest.data.mapper

import ru.yotfr.database.entity.WallpaperDownloadsEntity
import ru.yotfr.model.WallpaperDownload

fun ru.yotfr.database.entity.WallpaperDownloadsEntity.mapDomain(): ru.yotfr.model.WallpaperDownload {
    return ru.yotfr.model.WallpaperDownload(
        downloadId = downloadId,
        wallpaperId = wallpaperId
    )
}

fun ru.yotfr.model.WallpaperDownload.mapEntity(): ru.yotfr.database.entity.WallpaperDownloadsEntity {
    return ru.yotfr.database.entity.WallpaperDownloadsEntity(
        downloadId = downloadId,
        wallpaperId = wallpaperId
    )
}