package ru.yotfr.categorywallpapers

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalCategoryWallpapersRepositoryModule::class])
@InstallIn(SingletonComponent::class)
object PublicCategoryWallpapersRepositoryModule