package com.machmudow.weatherapp.domain.repository

import com.machmudow.weatherapp.common.ApiResult
import com.machmudow.weatherapp.domain.model.Weather

interface ForecastRepository {

    suspend fun getForecast(city: String): ApiResult<List<Weather>>
}
