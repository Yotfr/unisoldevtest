package ru.yotfr.installer

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [InternalInstallerModule::class])
@InstallIn(SingletonComponent::class)
object PublicInstallerModule