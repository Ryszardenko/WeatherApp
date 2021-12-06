package com.machmudow.weatherapp.common

fun String.isMorning() = this == MORNING
fun String.isDay() = this == DAY
fun String.isNight() = this == NIGHT

private const val MORNING = "09"
private const val DAY = "15"
private const val NIGHT = "00"
