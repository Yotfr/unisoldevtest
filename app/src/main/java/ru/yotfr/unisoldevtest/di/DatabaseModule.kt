package ru.yotfr.unisoldevtest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.datasource.local.dao.FavoriteWallpapersDao
import ru.yotfr.unisoldevtest.data.datasource.local.dao.WallpaperDownloadsDao
import ru.yotfr.unisoldevtest.data.datasource.local.database.WallpapersDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteWallpapersDatabase(
        @ApplicationContext context: Context
    ): WallpapersDatabase =
        Room.databaseBuilder(
            context,
            WallpapersDatabase::class.java,
            "fav_wallpapers_db"
        ).build()

    @Provides
    @Singleton
    fun provideFavoriteWallpapersDao(
        favoriteWallpapersDatabase: WallpapersDatabase
    ): FavoriteWallpapersDao {
        return favoriteWallpapersDatabase.favoriteWallpapersDao()
    }

    @Provides
    @Singleton
    fun provideWallpaperDownloadsDao(
        favoriteWallpapersDatabase: WallpapersDatabase
    ): WallpaperDownloadsDao {
        return favoriteWallpapersDatabase.wallpaperDownloadsDao()
    }

}