package ru.yotfr.network

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.yotfr.network.api.WallpaperApi
import ru.yotfr.network.api.WallpaperNetworkProvider
import ru.yotfr.network.api.WallpaperNetworkProviderImpl
import ru.yotfr.network.interceptor.LoggingInterceptor
import ru.yotfr.network.interceptor.NetworkConnectionInterceptor
import ru.yotfr.network.interceptor.TimeoutInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object InternalNetworkModule {

    @Provides
    @Singleton
    internal fun provideOkHttpClient(
        @ApplicationContext context: Context
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .addInterceptor(TimeoutInterceptor())
            .addInterceptor(NetworkConnectionInterceptor(context))
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://pixabay.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideWallpaperApi(
        retrofit: Retrofit
    ): WallpaperApi {
        return retrofit.create(WallpaperApi::class.java)
    }

    @Provides
    fun provideWallpaperNetworkProvider(
        wallpaperApi: WallpaperApi
    ): WallpaperNetworkProvider {
        return WallpaperNetworkProviderImpl(wallpaperApi)
    }
}