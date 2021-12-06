package com.machmudow.weatherapp.data.remote

import com.machmudow.weatherapp.BuildConfig
import com.machmudow.weatherapp.data.remote.dto.ForecastResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("forecast?appid=${BuildConfig.API_KEY}&units=metric")
    suspend fun getForecast(
        @Query("q") city: String
    ): ForecastResponseDto
}
