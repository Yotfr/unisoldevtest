package ru.yotfr.unisoldevtest.data.mapper

import ru.yotfr.database.entity.WallpaperDownloadsEntity
import ru.yotfr.unisoldevtest.domain.model.WallpaperDownload

fun ru.yotfr.database.entity.WallpaperDownloadsEntity.mapDomain(): WallpaperDownload {
    return WallpaperDownload(
        downloadId = downloadId,
        wallpaperId = wallpaperId
    )
}

fun WallpaperDownload.mapEntity(): ru.yotfr.database.entity.WallpaperDownloadsEntity {
    return ru.yotfr.database.entity.WallpaperDownloadsEntity(
        downloadId = downloadId,
        wallpaperId = wallpaperId
    )
}