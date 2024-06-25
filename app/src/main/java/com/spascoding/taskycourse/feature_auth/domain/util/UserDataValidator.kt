package com.spascoding.taskycourse.feature_auth.domain.util

import androidx.core.util.PatternsCompat
import com.spascoding.taskycourse.core.data.Result
import com.spascoding.taskycourse.core.domain.Error

object UserDataValidator {
    fun validateName(name: String): Result<Unit, NameError> {
        if (name.length < 4) {
            return Result.Error(NameError.TOO_SHORT)
        }
        if (name.length > 50) {
            return Result.Error(NameError.TOO_LONG)
        }
        return Result.Success(Unit)
    }
    enum class NameError : Error {
        TOO_SHORT,
        TOO_LONG,
    }

    fun validateEmail(text: String): Result<Unit, EmailError> {
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(text).matches()) {
            return Result.Error(EmailError.INVALID_EMAIL)
        }
        return Result.Success(Unit)
    }
    enum class EmailError: Error {
        INVALID_EMAIL,
    }

    fun validatePassword(password: String): Result<Unit, PasswordError> {
        if (password.length < 9) {
            return Result.Error(PasswordError.TOO_SHORT)
        }
        val hasLowerCase = password.any { it.isLowerCase() }
        if (!hasLowerCase) {
            return Result.Error(PasswordError.NO_LOWERCASE)
        }
        val hasUpperCase = password.any { it.isUpperCase() }
        if (!hasUpperCase) {
            return Result.Error(PasswordError.NO_UPPERCASE)
        }
        val hasDigit = password.any { it.isDigit() }
        if (!hasDigit) {
            return Result.Error(PasswordError.NO_DIGIT)
        }
        return Result.Success(Unit)
    }
    enum class PasswordError : Error {
        TOO_SHORT,
        NO_LOWERCASE,
        NO_UPPERCASE,
        NO_DIGIT,
    }
}