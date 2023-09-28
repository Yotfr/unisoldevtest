package ru.yotfr.categories

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.yotfr.categories.repository.CategoriesRepository
import ru.yotfr.categories.repository.CategoriesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface InternalCategoriesRepositoryModule {
    @Binds
    @Singleton
    fun bindCategoriesRepository(
        categoriesRepositoryImpl: CategoriesRepositoryImpl
    ): CategoriesRepository
}