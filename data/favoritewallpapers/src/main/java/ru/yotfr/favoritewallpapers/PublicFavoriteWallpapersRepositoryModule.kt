package ru.yotfr.favoritewallpapers

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalFavoriteWallpapersRepositoryModule::class])
@InstallIn(SingletonComponent::class)
object PublicFavoriteWallpapersRepositoryModule