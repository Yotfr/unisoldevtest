package ru.yotfr.memorycache

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalPagingCacheModule::class])
@InstallIn(SingletonComponent::class)
object PublicPagingCacheModule