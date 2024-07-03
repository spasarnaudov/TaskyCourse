package com.spascoding.taskycourse.feature_agenda.presentation.utils

import com.google.common.truth.Truth
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class MonthTest {

    @ParameterizedTest
    @CsvSource(
        "0, January",
        "1, February",
        "2, March",
        "3, April",
        "4, May",
        "5, June",
        "6, July",
        "7, August",
        "8, September",
        "9, October",
        "10, November",
        "11, December",
    )
    fun `test getting of current month`(monthNumber: Int, expected: String) {
        val actual = Month.get(monthNumber)
        Truth.assertThat(actual.description).isEqualTo(expected)
    }

}