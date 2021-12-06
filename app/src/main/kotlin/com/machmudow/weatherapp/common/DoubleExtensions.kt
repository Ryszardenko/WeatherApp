package com.machmudow.weatherapp.common

fun Double?.toTemperature(): String = this?.toString() ?: NOT_AVAILABLE

private const val NOT_AVAILABLE = "N/A"
