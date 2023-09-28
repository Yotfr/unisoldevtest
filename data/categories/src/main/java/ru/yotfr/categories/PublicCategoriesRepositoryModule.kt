package ru.yotfr.categories

import dagger.Module
import javax.inject.Singleton

@Module(includes = [InternalCategoriesRepositoryModule::class])
@Singleton
object PublicCategoriesRepositoryModule