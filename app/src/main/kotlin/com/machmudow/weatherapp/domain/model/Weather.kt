package com.machmudow.weatherapp.domain.model

data class Weather(
    val date: String,
    val morningTemperature: String,
    val dayTemperature: String,
    val nightTemperature: String,
    val minTemperature: String,
    val maxTemperature: String,
    val meanTemperature: Double,
    val humidity: Int,
)
