package com.barker.takehomechallenge.di

import com.barker.takehomechallenge.network.interceptor.RoutesInterceptor
import com.barker.takehomechallenge.network.service.RoutesService
import com.barker.takehomechallenge.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    @Singleton
    internal fun providesRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun providesClient(weatherInterceptor: RoutesInterceptor): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(weatherInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    internal fun providesInterceptor(): RoutesInterceptor = RoutesInterceptor()

    @Provides
    @Singleton
    internal fun providesWeatherService(retrofit: Retrofit): RoutesService = retrofit.create(
        RoutesService::class.java)
}