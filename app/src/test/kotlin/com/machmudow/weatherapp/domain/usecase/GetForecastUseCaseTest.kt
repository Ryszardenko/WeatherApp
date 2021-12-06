package com.machmudow.weatherapp.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.machmudow.weatherapp.common.ApiResult
import com.machmudow.weatherapp.domain.model.WeatherState
import com.machmudow.weatherapp.domain.repository.ForecastRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetForecastUseCaseTest {

    private val mockedRepository = mockk<ForecastRepository>()
    private val useCase = GetForecastUseCase(mockedRepository)
    private val initState = WeatherState.Init

    @Test
    fun `should start with Init state`() = runBlockingTest {
        // when
        useCase.state.test {
            // then
            assertThat(awaitItem()).isEqualTo(initState)

            // clean
            cancelAndConsumeRemainingEvents()
        }
    }

    @Test
    fun `should call getForecast and set Loading and then Success`() = runBlockingTest {
        // given
        val expected = WeatherState.Loading
        val expected2 = WeatherState.Success("test", emptyList())
        coEvery { mockedRepository.getForecast("test") } returns ApiResult.Success(emptyList())

        useCase.state.test {
            // when
            useCase.getForecast("test")

            // then
            coVerify { mockedRepository.getForecast("test") }
            assertThat(awaitItem()).isEqualTo(initState)
            assertThat(awaitItem()).isEqualTo(expected)
            assertThat(awaitItem()).isEqualTo(expected2)

            // clean
            cancelAndConsumeRemainingEvents()
        }
    }
}
