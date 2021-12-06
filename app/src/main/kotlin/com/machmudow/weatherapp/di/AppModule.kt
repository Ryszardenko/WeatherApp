package com.machmudow.weatherapp.di

import com.machmudow.weatherapp.BuildConfig
import com.machmudow.weatherapp.data.remote.ApiService
import com.machmudow.weatherapp.data.repository.ForecastRepositoryImpl
import com.machmudow.weatherapp.domain.repository.ForecastRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesOkHttp(
        interceptors: Set<@JvmSuppressWildcards Interceptor>
    ): OkHttpClient = OkHttpClient.Builder()
        .apply { interceptors.forEach(::addInterceptor) }
        .build()

    @Provides
    @IntoSet
    fun providesHttpInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun providesMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Provides
    fun providesConverter(moshi: Moshi): Converter.Factory = MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        converter: Converter.Factory,
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(converter)
        .baseUrl(BuildConfig.BASE_URL)
        .build()


    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideListRepository(apiService: ApiService): ForecastRepository {
        return ForecastRepositoryImpl(apiService)
    }
}
