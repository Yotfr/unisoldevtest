package ru.yotfr.connectivity

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalConnectivityModule::class])
@InstallIn(SingletonComponent::class)
object PublicConnectivityModule