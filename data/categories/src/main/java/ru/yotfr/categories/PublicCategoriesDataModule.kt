package ru.yotfr.categories

import dagger.Module
import javax.inject.Singleton

@Module(includes = [InternalCategoriesDataModule::class])
@Singleton
object PublicCategoriesDataModule