package com.spascoding.taskycourse.feature_auth.presentation.util

import com.google.common.truth.Truth.assertThat
import org.junit.Assert
import org.junit.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NameInitialsExtractorTest {

    @ParameterizedTest
    @CsvSource(
        "Spas, SP",
        "Spas Arnaudov, SA",
        "Spas    Arnaudov, SA",
        "Spas Georgiev Arnaudov, SA",
    )
    fun testExtractInitials(name: String, expected: String) {
        val actual = NameInitialsExtractor(name).extract()
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Extract form empty word`() {
        val emptyWordName = ""
        val exception = Assert.assertThrows(IllegalArgumentException::class.java) {
            NameInitialsExtractor(emptyWordName).extract()
        }
        assertThat(exception).hasMessageThat().isEqualTo("Name must contains minimum two letters")
    }

}