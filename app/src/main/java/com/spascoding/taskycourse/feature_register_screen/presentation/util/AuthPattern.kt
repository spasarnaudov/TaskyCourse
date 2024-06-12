package com.spascoding.taskycourse.feature_register_screen.presentation.util

import android.util.Patterns

object AuthPattern {
    fun isValidName(text: String): Boolean = text.length in 4..50
    fun isValidEmail(text: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(text).matches()
    fun isValidPassword(password: String): Boolean {
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasValidLength = password.length > 9

        return hasLowerCase && hasUpperCase && hasDigit && hasValidLength
    }
}