package com.machmudow.weatherapp.data.repository

import com.machmudow.weatherapp.common.*
import com.machmudow.weatherapp.data.apiCall
import com.machmudow.weatherapp.data.remote.ApiService
import com.machmudow.weatherapp.data.remote.dto.ForecastDto
import com.machmudow.weatherapp.data.remote.dto.ForecastResponseDto
import com.machmudow.weatherapp.domain.model.Weather
import com.machmudow.weatherapp.domain.repository.ForecastRepository
import javax.inject.Inject

internal class ForecastRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ForecastRepository {

    override suspend fun getForecast(city: String): ApiResult<List<Weather>> =
        apiCall(
            request = { apiService.getForecast(city) },
            successMapper = { response -> response.groupByDay() }
        )

    private fun ForecastResponseDto.groupByDay(): List<Weather> {
        val groupedByDay: Map<String, List<ForecastDto>> =
            list.groupBy { it.dateTime.apiDateToDisplayDateFormat() }

        return groupedByDay.entries.map { map ->
            Weather(
                date = map.key.apiDateToDisplayDateFormat(),
                morningTemperature = map.value.filter {
                    it.dateTime.apiDateToDisplayHourFormat().isMorning()
                }.map { it.main.temp }.average().toTemperature(),
                dayTemperature = map.value.filter {
                    it.dateTime.apiDateToDisplayHourFormat().isDay()
                }.map { it.main.temp }.average().toTemperature(),
                nightTemperature = map.value.filter {
                    it.dateTime.apiDateToDisplayHourFormat().isNight()
                }.map { it.main.temp }.average().toTemperature(),
                minTemperature = map.value.map { it.main.tempMin }.minOrNull().toTemperature(),
                maxTemperature = map.value.map { it.main.tempMax }.maxOrNull().toTemperature(),
                meanTemperature = map.value.map { it.main.temp }.average(),
                humidity = map.value.map { it.main.humidity }.average().toInt(),
            )
        }
    }
}
