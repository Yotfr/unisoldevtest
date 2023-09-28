package ru.yotfr.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.database.dao.FavoriteWallpapersDao
import ru.yotfr.database.dao.WallpaperDownloadsDao
import ru.yotfr.database.database.WallpapersDatabase
import ru.yotfr.database.provider.FavoriteWallpaperDatabaseProvider
import ru.yotfr.database.provider.FavoriteWallpaperDatabaseProviderImpl
import ru.yotfr.database.provider.WallpaperDownloadsDatabaseProvider
import ru.yotfr.database.provider.WallpaperDownloadsDatabaseProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalDatabaseModule {

    @Provides
    @Singleton
    internal fun provideFavoriteWallpapersDatabase(
        @ApplicationContext context: Context
    ): WallpapersDatabase =
        Room.databaseBuilder(
            context,
            WallpapersDatabase::class.java,
            "fav_wallpapers_db"
        ).build()

    @Provides
    @Singleton
    internal fun provideFavoriteWallpapersDao(
        favoriteWallpapersDatabase: WallpapersDatabase
    ): FavoriteWallpapersDao {
        return favoriteWallpapersDatabase.favoriteWallpapersDao()
    }

    @Provides
    @Singleton
    internal fun provideWallpaperDownloadsDao(
        favoriteWallpapersDatabase: WallpapersDatabase
    ): WallpaperDownloadsDao {
        return favoriteWallpapersDatabase.wallpaperDownloadsDao()
    }

    @Provides
    @Singleton
    fun provideFavoriteWallpaperDatabaseProvider(
        favoriteWallpapersDao: FavoriteWallpapersDao
    ): FavoriteWallpaperDatabaseProvider {
        return FavoriteWallpaperDatabaseProviderImpl(favoriteWallpapersDao)
    }

    @Provides
    @Singleton
    fun provideWallpaperDownloadsDatabaseProvider(
        wallpaperDownloadsDao: WallpaperDownloadsDao
    ): WallpaperDownloadsDatabaseProvider {
        return WallpaperDownloadsDatabaseProviderImpl(wallpaperDownloadsDao)
    }

}