package com.machmudow.weatherapp.common

import java.text.SimpleDateFormat
import java.util.*

fun String.apiDateToDisplayDateFormat(): String = apiDateFormatTo(dateFormat)

fun String.apiDateToDisplayHourFormat(): String = apiDateFormatTo(hourFormat)

private fun String.apiDateFormatTo(dateFormat: SimpleDateFormat) =
    runCatching { apiFormat.parse(this) }
        .getOrNull()
        ?.let(dateFormat::format)
        ?: this

private val apiFormat by lazy {
    SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss", Locale.UK)
}

private val dateFormat by lazy {
    SimpleDateFormat("dd-MM-yyyy", Locale.UK)
}

private val hourFormat by lazy {
    SimpleDateFormat("HH", Locale.UK)
}
