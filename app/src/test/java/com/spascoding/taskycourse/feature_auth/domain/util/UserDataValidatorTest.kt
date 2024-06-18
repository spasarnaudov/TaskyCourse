package com.spascoding.taskycourse.feature_auth.domain.util

import org.junit.Assert.assertEquals
import org.junit.Test
import com.spascoding.taskycourse.core.data.Result

class UserDataValidatorTest {
    @Test
    fun `validateName returns TOO_SHORT error for names shorter than 4 characters`() {
        val result = UserDataValidator.validateName("abc")
        assertEquals(Result.Error(UserDataValidator.NameError.TOO_SHORT), result)
    }

    @Test
    fun `validateName returns TOO_LONG error for names longer than 50 characters`() {
        val longName = "a".repeat(51)
        val result = UserDataValidator.validateName(longName)
        assertEquals(Result.Error(UserDataValidator.NameError.TOO_LONG), result)
    }

    @Test
    fun `validateName returns Success for valid names`() {
        val result = UserDataValidator.validateName("ValidName")
        assertEquals(Result.Success(Unit), result)
    }

//    @Test
//    fun `validateEmail returns INVALID_EMAIL error for invalid email format`() {
//        val result = UserDataValidator.validateEmail("invalid-email")
//        assertEquals(Result.Error(UserDataValidator.EmailError.INVALID_EMAIL), result)
//    }
//
//    @Test
//    fun `validateEmail returns Success for valid email`() {
//        val result = UserDataValidator.validateEmail("valid@example.com")
//        assertEquals(Result.Success(Unit), result)
//    }

    @Test
    fun `validatePassword returns TOO_SHORT error for passwords shorter than 9 characters`() {
        val result = UserDataValidator.validatePassword("short1A")
        assertEquals(Result.Error(UserDataValidator.PasswordError.TOO_SHORT), result)
    }

    @Test
    fun `validatePassword returns NO_LOWERCASE error for passwords with no lowercase letters`() {
        val result = UserDataValidator.validatePassword("PASSWORD123")
        assertEquals(Result.Error(UserDataValidator.PasswordError.NO_LOWERCASE), result)
    }

    @Test
    fun `validatePassword returns NO_UPPERCASE error for passwords with no uppercase letters`() {
        val result = UserDataValidator.validatePassword("password123")
        assertEquals(Result.Error(UserDataValidator.PasswordError.NO_UPPERCASE), result)
    }

    @Test
    fun `validatePassword returns NO_DIGIT error for passwords with no digits`() {
        val result = UserDataValidator.validatePassword("PasswordWithoutDigit")
        assertEquals(Result.Error(UserDataValidator.PasswordError.NO_DIGIT), result)
    }

    @Test
    fun `validatePassword returns Success for valid passwords`() {
        val result = UserDataValidator.validatePassword("Valid1Password")
        assertEquals(Result.Success(Unit), result)
    }
}