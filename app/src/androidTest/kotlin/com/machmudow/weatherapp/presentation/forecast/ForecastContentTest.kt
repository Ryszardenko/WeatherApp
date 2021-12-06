package com.machmudow.weatherapp.presentation.forecast

import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.machmudow.weatherapp.R
import com.machmudow.weatherapp.common.ApiResult
import com.machmudow.weatherapp.domain.model.WeatherState
import org.junit.Rule
import org.junit.Test

class ForecastContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun shouldDisplayQuery() {
        // given
        val query = "query"

        // when
        composeRule.setContent {
            ForecastContent(
                WeatherState.Init, query, {}, {}, {}
            )
        }

        // then
        composeRule.onNodeWithText(query).assertExists()
    }

    @Test
    fun shouldDisplayErrorWhenErrorState() {
        // given
        val errorMsg = ApplicationProvider.getApplicationContext<Context>()
            .getString(R.string.error_please_try_again_later)

        // when
        composeRule.setContent {
            ForecastContent(
                WeatherState.Error(ApiResult.Failure.Error.Unknown), "", {}, {}, {}
            )
        }

        // then
        composeRule.onNodeWithText(errorMsg).assertExists()
    }

    @Test
    fun shouldHandleOnValueChange() {
        // given
        val enterText = ApplicationProvider.getApplicationContext<Context>()
            .getString(R.string.enter_city)
        var testCount = 0
        val expected = 1

        // when
        composeRule.setContent {
            ForecastContent(
                WeatherState.Init, "", { testCount++ }, { }, {},
            )
        }

        composeRule.onNodeWithText(enterText).performTextInput("A")

        assertThat(testCount).isEqualTo(expected)
    }
}
