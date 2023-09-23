package ru.yotfr.unisoldevtest.data.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.yotfr.unisoldevtest.data.datasource.local.dao.FavoriteWallpapersDao
import ru.yotfr.unisoldevtest.data.datasource.local.dao.WallpaperDownloadsDao
import ru.yotfr.unisoldevtest.data.datasource.local.entity.WallpaperDownloadsEntity
import ru.yotfr.unisoldevtest.data.datasource.local.entity.WallpaperEntity

@Database(
    entities = [
        WallpaperEntity::class,
        WallpaperDownloadsEntity::class
    ],
    version = 1
)
abstract class WallpapersDatabase : RoomDatabase() {
    abstract fun favoriteWallpapersDao(): FavoriteWallpapersDao
    abstract fun wallpaperDownloadsDao(): WallpaperDownloadsDao
}