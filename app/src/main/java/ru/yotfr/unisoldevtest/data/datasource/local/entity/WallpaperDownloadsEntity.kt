package ru.yotfr.unisoldevtest.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "wallpaper_downloads",
    primaryKeys = ["downloadId","wallpaperId"]
)
data class WallpaperDownloadsEntity(
    val downloadId: Long,
    val wallpaperId: String
)