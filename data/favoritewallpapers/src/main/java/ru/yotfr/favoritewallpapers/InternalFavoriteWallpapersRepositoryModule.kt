package ru.yotfr.favoritewallpapers

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.favoritewallpapers.repository.FavoriteWallpapersRepository
import ru.yotfr.favoritewallpapers.repository.FavoriteWallpapersRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalFavoriteWallpapersRepositoryModule {

    @Binds
    @Singleton
    fun bindFavoriteWallpapersRepository(
        favoriteWallpapersRepositoryImpl: FavoriteWallpapersRepositoryImpl
    ): FavoriteWallpapersRepository
}