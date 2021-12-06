package com.machmudow.weatherapp.presentation.forecast

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.machmudow.weatherapp.domain.model.WeatherState
import com.machmudow.weatherapp.R
import com.machmudow.weatherapp.presentation.components.WeatherComponent
import com.machmudow.weatherapp.presentation.theme.SecondaryErrorRed

@Composable
internal fun ForecastScreen(
    viewModel: ForecastViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val query = viewModel.query

    ForecastContent(
        state = state,
        query = query,
        onValueChange = viewModel::onQueryChange,
        onCloseClicked = viewModel::onCloseClicked,
        onSearchClicked = viewModel::getForecast,
    )
}

@Composable
internal fun ForecastContent(
    state: WeatherState,
    query: String,
    onValueChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            SearchComponent(
                query = query,
                onValueChange = onValueChange,
                onCloseClicked = onCloseClicked,
                onSearchClicked = onSearchClicked
            )
        }

        when (val currentState: WeatherState = state) {
            is WeatherState.Init -> Unit
            is WeatherState.Loading -> {
                item {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
            /**
             * In prod app I would set separate flow for errors
             */
            is WeatherState.Error -> {
                item {
                    Text(
                        text = stringResource(R.string.error_please_try_again_later),
                        color = SecondaryErrorRed,
                    )
                }
            }
            is WeatherState.Success -> {
                items(items = currentState.weather) { weather ->
                    WeatherComponent(weather = weather)
                }
            }
        }
    }
}

@Composable
private fun SearchComponent(
    query: String,
    onValueChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: () -> Unit,
) {
    TextField(
        value = query,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(text = stringResource(R.string.enter_city))
        },
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(R.string.search_icon),
                modifier = Modifier.clickable { onSearchClicked() }
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(R.string.close_icon),
                modifier = Modifier.clickable { onCloseClicked() }
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = { onSearchClicked() }
        )
    )
}
