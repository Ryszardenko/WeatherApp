package com.machmudow.weatherapp.presentation.forecast

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.machmudow.weatherapp.domain.usecase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ForecastViewModel @Inject constructor(
    private val useCase: GetForecastUseCase,
) : ViewModel() {

    val state = useCase.state
    var query by mutableStateOf(EMPTY_QUERY)
        private set

    fun getForecast() {
        viewModelScope.launch {
            useCase.getForecast(query)
        }
    }

    fun onQueryChange(query: String) {
        this.query = query
    }

    fun onCloseClicked() {
        this.query = EMPTY_QUERY
    }

    companion object {
        private const val EMPTY_QUERY = ""
    }
}
