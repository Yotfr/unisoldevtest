package ru.yotfr.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.yotfr.database.dao.FavoriteWallpapersDao
import ru.yotfr.database.dao.WallpaperDownloadsDao
import ru.yotfr.database.entity.WallpaperDownloadsEntity
import ru.yotfr.database.entity.WallpaperEntity

@Database(
    entities = [
        WallpaperEntity::class,
        WallpaperDownloadsEntity::class
    ],
    version = 1
)
internal abstract class WallpapersDatabase : RoomDatabase() {
    abstract fun favoriteWallpapersDao(): FavoriteWallpapersDao
    abstract fun wallpaperDownloadsDao(): WallpaperDownloadsDao
}