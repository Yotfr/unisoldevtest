package ru.yotfr.unisoldevtest.data.mapper

import ru.yotfr.unisoldevtest.data.datasource.local.entity.WallpaperDownloadsEntity
import ru.yotfr.unisoldevtest.domain.model.WallpaperDownload

fun WallpaperDownloadsEntity.mapDomain(): WallpaperDownload {
    return WallpaperDownload(
        downloadId = downloadId,
        wallpaperId = wallpaperId
    )
}

fun WallpaperDownload.mapEntity(): WallpaperDownloadsEntity {
    return WallpaperDownloadsEntity(
        downloadId = downloadId,
        wallpaperId = wallpaperId
    )
}