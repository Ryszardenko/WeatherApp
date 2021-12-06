package com.machmudow.weatherapp.presentation.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@SuppressLint("ConflictingOnColor")
private val LightColors = lightColors(
    primary = MainCharcoal,
    primaryVariant = MainSandyGrey,
    onPrimary = MainWhite,
    secondary = SecondaryGreen,
    onSecondary = SecondaryBlack,
    error = SecondaryErrorRed
)

private val DarkColors = darkColors(
    primary = MainCharcoal,
    primaryVariant = MainSandyGrey,
    onPrimary = MainWhite,
    secondary = SecondaryGreen,
    onSecondary = SecondaryBlack,
    error = SecondaryErrorRed
)

@Composable
fun WeatherTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColors else LightColors,
        typography = WeatherTypography,
        shapes = WeatherShapes,
        content = content
    )
}
