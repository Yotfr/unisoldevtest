package ru.yotfr.installer

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.installer.installer.Installer
import ru.yotfr.installer.installer.InstallerImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalInstallerModule {

    @Provides
    @Singleton
    fun provideInstaller(
        @ApplicationContext context: Context
    ) : Installer {
        return InstallerImpl(context)
    }
}