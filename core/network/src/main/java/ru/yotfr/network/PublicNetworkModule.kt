package ru.yotfr.network

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalNetworkModule::class])
@InstallIn(SingletonComponent::class)
interface PublicNetworkModule