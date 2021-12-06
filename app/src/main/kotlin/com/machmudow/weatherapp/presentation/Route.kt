package com.machmudow.weatherapp.presentation

sealed class Route(val route: String) {
    object Forecast: Route("forecast")
    object Details: Route("details")
}
