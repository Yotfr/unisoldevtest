package ru.yotfr.shared

import ru.yotfr.database.entity.WallpaperDownloadsEntity
import ru.yotfr.shared.model.WallpaperDownload

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