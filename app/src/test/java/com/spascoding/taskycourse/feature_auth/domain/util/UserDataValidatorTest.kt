package com.spascoding.taskycourse.feature_auth.domain.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import com.spascoding.taskycourse.core.data.Result

class UserDataValidatorTest {
    @Test
    fun `validateName returns TOO_SHORT error for names shorter than 4 characters`() {
        val shortName = "abc"
        val actual = UserDataValidator.validateName(shortName)
        val expected = Result.Error(UserDataValidator.NameError.TOO_SHORT)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `validateName returns TOO_LONG error for names longer than 50 characters`() {
        val longName = "a".repeat(51)
        val actual = UserDataValidator.validateName(longName)
        val expected = Result.Error(UserDataValidator.NameError.TOO_LONG)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `validateName returns Success for valid names`() {
        val validName = "ValidName"
        val actual = UserDataValidator.validateName(validName)
        val expected = Result.Success(Unit)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `validatePassword returns TOO_SHORT error for passwords shorter than 9 characters`() {
        val shortPassword = "short1A"
        val actual = UserDataValidator.validatePassword(shortPassword)
        val expected = Result.Error(UserDataValidator.PasswordError.TOO_SHORT)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `validatePassword returns NO_LOWERCASE error for passwords with no lowercase letters`() {
        val noLowercasePassword = "PASSWORD123"
        val actual = UserDataValidator.validatePassword(noLowercasePassword)
        val expected = Result.Error(UserDataValidator.PasswordError.NO_LOWERCASE)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `validatePassword returns NO_UPPERCASE error for passwords with no uppercase letters`() {
        val noUppercasePassword = "password123"
        val actual = UserDataValidator.validatePassword(noUppercasePassword)
        val expected = Result.Error(UserDataValidator.PasswordError.NO_UPPERCASE)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `validatePassword returns NO_DIGIT error for passwords with no digits`() {
        val noDigitPassword = "PasswordWithoutDigit"
        val actual = UserDataValidator.validatePassword(noDigitPassword)
        val expected = Result.Error(UserDataValidator.PasswordError.NO_DIGIT)
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `validatePassword returns Success for valid passwords`() {
        val validPassword = "Valid1Password"
        val actual = UserDataValidator.validatePassword(validPassword)
        val expected = Result.Success(Unit)
        assertThat(actual).isEqualTo(expected)
    }
}