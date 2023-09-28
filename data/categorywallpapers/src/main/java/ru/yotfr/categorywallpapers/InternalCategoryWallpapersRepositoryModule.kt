package ru.yotfr.categorywallpapers

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.categorywallpapers.repository.CategoryWallpapersRepository
import ru.yotfr.categorywallpapers.repository.CategoryWallpapersRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalCategoryWallpapersRepositoryModule {

    @Binds
    @Singleton
    fun bindCategoryWallpapersRepository(
        categoryWallpapersRepositoryImpl: CategoryWallpapersRepositoryImpl
    ): CategoryWallpapersRepository
}