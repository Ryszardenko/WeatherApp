package com.machmudow.weatherapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.machmudow.weatherapp.R
import com.machmudow.weatherapp.domain.model.Weather
import com.machmudow.weatherapp.presentation.theme.MainPrimary
import com.machmudow.weatherapp.presentation.theme.MainSandyGrey
import com.machmudow.weatherapp.presentation.theme.SPACING_S

@Composable
internal fun WeatherComponent(
    weather: Weather,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(SPACING_S.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = weather.date)
        Column {
            Text(
                text = stringResource(R.string.morning_temperature, weather.morningTemperature),
                color = MaterialTheme.colors.primaryVariant,
            )
            Text(
                text = stringResource(R.string.day_temperature, weather.dayTemperature),
                color = MaterialTheme.colors.secondary,
            )
            Text(
                text = stringResource(R.string.night_temperature, weather.nightTemperature),
                color = Color.Blue,
            )
            Text(
                text = stringResource(R.string.humidity, weather.humidity),
                color = Color.Magenta,
            )
            Text(
                text = stringResource(R.string.min_temperature, weather.minTemperature),
                color = Color.Blue,
            )
            Text(
                text = stringResource(R.string.max_temperature, weather.maxTemperature),
                color = MainPrimary,
            )
            Text(
                text = stringResource(R.string.mean_temperature, weather.meanTemperature),
                color = MainSandyGrey,
            )
        }
    }
}
