package ru.yotfr.categories

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalCategoriesRepositoryModule::class])
@InstallIn(SingletonComponent::class)
object PublicCategoriesRepositoryModule