package com.machmudow.weatherapp.common

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class DateFormatterTest {

    @Test
    fun `Should properly format api date to display hours`() {
        // given
        val testData = listOf(
            "2021-05-28 13:01:55",
            "2020-12-15 03:19:55",
            "2022-02-14 18:05:55",
        )
        val expected = listOf(
            "13",
            "03",
            "18"
        )

        // when
        val actual = testData.map(String::apiDateToDisplayHourFormat)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Should properly format api date to display date`() {
        // given
        val testData = listOf(
            "2021-05-28 13:01:55",
            "2020-12-15 03:19:55",
            "2022-02-14 18:05:55",
        )
        val expected = listOf(
            "28-05-2021",
            "15-12-2020",
            "14-02-2022"
        )

        // when
        val actual = testData.map(String::apiDateToDisplayDateFormat)

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Should return original date when cannot parse`() {
        // given
        val testData = listOf(
            "2021-",
            "tomorrow",
            "hello test",
        )

        // when
        val actual = testData.map(String::apiDateToDisplayDateFormat)

        // then
        assertThat(actual).isEqualTo(testData)
    }
}
