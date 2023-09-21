package ru.yotfr.unisoldevtest.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "wallpapers"
)
data class WallpaperEntity(
    @PrimaryKey val id: String,
    val url: String,
    val previewUrl: String
)
