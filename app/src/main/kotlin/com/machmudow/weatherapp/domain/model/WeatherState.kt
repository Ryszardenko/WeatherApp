package com.machmudow.weatherapp.domain.model

import com.machmudow.weatherapp.common.ApiResult

internal sealed interface WeatherState {
    object Init : WeatherState
    object Loading : WeatherState
    data class Success(val city: String, val weather: List<Weather>) : WeatherState
    @JvmInline
    value class Error(val error: ApiResult.Failure.Error) : WeatherState
}
