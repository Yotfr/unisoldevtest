package ru.yotfr.unisoldevtest.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.unisoldevtest.data.datasource.local.dao.FavoriteWallpapersDao
import ru.yotfr.unisoldevtest.data.datasource.local.database.FavoriteWallpapersDatabase

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun provideFavoriteWallpapersDatabase(
        @ApplicationContext context: Context
    ): FavoriteWallpapersDatabase =
        Room.databaseBuilder(
            context,
            FavoriteWallpapersDatabase::class.java,
            "fav_wallpapers_db"
        ).build()

    @Provides
    fun provideFavoriteWallpapersDao(
        favoriteWallpapersDatabase: FavoriteWallpapersDatabase
    ): FavoriteWallpapersDao {
        return favoriteWallpapersDatabase.favoriteWallpapersDao()
    }

}