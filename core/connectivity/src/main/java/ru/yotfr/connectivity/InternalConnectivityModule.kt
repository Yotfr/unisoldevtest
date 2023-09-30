package ru.yotfr.connectivity

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.yotfr.connectivity.connectivityprovider.ConnectivityProvider
import ru.yotfr.connectivity.connectivityprovider.ConnectivityProviderImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalConnectivityModule {

    @Provides
    @Singleton
    fun provideConnectivityProvider(
        @ApplicationContext context: Context
    ): ConnectivityProvider {
        return ConnectivityProviderImpl(context)
    }
}