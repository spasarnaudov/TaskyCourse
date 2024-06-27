package com.spascoding.taskycourse.feature_auth.presentation.util

import com.google.common.truth.Truth.assertThat
import org.junit.Assert
import org.junit.Test

class NameInitialsExtractorTest {

    @Test
    fun `Extract form one word`() {
        val oneWordName = "Spas"
        val actual = NameInitialsExtractor(oneWordName).extract()
        val expected = "SP"
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `Extract form multiple word`() {
        val multipleWordName = "Spas Arnaudov"
        val actual = NameInitialsExtractor(multipleWordName).extract()
        val expected = "SA"
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