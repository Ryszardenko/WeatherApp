package com.machmudow.weatherapp.data.remote.dto

import com.squareup.moshi.Json

data class ForecastResponseDto(
    @Json(name = "cod") val cod: String,
    @Json(name = "message") val message: Int,
    @Json(name = "cnt") val cnt: Int,
    @Json(name = "list") val list: List<ForecastDto>,
    @Json(name = "city") val city: CityDto,
)

data class ForecastDto(
    @Json(name = "dt") val dt: Int,
    @Json(name = "main") val main: MainDto,
    @Json(name = "dt_txt") val dateTime: String,
    @Json(name = "weather") val weather: List<WeatherDto>,
)

data class MainDto(
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "humidity") val humidity: Int,
    @Json(name = "pressure") val pressure: Int,
    @Json(name = "temp") val temp: Double,
    @Json(name = "temp_max") val tempMax: Double,
    @Json(name = "temp_min") val tempMin: Double
)

data class CityDto(
    @Json(name = "id") val id: Int,
    @Json(name = "name") val name: String,
    @Json(name = "country") val country: String,
)

data class WeatherDto(
    @Json(name = "id") val id: Int,
    @Json(name = "main") val main: String,
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String,
)

