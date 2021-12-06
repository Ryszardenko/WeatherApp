package com.machmudow.weatherapp.domain.usecase

import com.machmudow.weatherapp.common.onFailure
import com.machmudow.weatherapp.common.onSuccess
import com.machmudow.weatherapp.domain.model.WeatherState
import com.machmudow.weatherapp.domain.repository.ForecastRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@ViewModelScoped
internal class GetForecastUseCase @Inject constructor(
    private val repository: ForecastRepository
) {
    private val _state: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState.Init)
    val state: StateFlow<WeatherState> = _state

    suspend fun getForecast(city: String) {
        _state.value = WeatherState.Loading
        repository.getForecast(city)
            .onSuccess { forecast -> _state.value = WeatherState.Success(city, forecast) }
            .onFailure { error -> _state.value = WeatherState.Error(error) }
    }
}
