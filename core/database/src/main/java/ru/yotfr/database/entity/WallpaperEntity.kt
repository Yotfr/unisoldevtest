package ru.yotfr.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "wallpapers"
)
data class WallpaperEntity(
    @PrimaryKey val id: String,
    val url: String,
    val previewUrl: String,
    val isFavorite: Boolean,
    val previewWidth: Int,
    val previewHeight: Int
)
