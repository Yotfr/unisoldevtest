package ru.yotfr.unisoldevtest.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.yotfr.unisoldevtest.data.datasource.remote.api.WallpaperApi
import ru.yotfr.unisoldevtest.data.datasource.remote.interceptor.LoggingInterceptor
import ru.yotfr.unisoldevtest.data.datasource.remote.interceptor.NetworkConnectionInterceptor
import ru.yotfr.unisoldevtest.data.datasource.remote.interceptor.TimeoutInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttp(
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
    fun provideRetrofit(
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
    fun provideWallpaperApi(
        retrofit: Retrofit
    ): WallpaperApi {
        return retrofit.create(WallpaperApi::class.java)
    }

}