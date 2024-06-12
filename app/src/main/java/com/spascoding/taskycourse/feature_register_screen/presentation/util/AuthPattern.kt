package com.spascoding.taskycourse.feature_register_screen.presentation.util

import android.util.Patterns

object AuthPattern {
    fun name(text: String): Boolean = text.length in 4..50
    fun email(text: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(text).matches()
    fun password(text: String): Boolean {
        // Check if the length is at least 9 characters
        if (text.length < 9) return false

        // Initialize flags for each condition
        var hasLowercase = false
        var hasUppercase = false
        var hasDigit = false

        // Iterate through each character in the text
        for (char in text) {
            when {
                char.isLowerCase() -> hasLowercase = true
                char.isUpperCase() -> hasUppercase = true
                char.isDigit() -> hasDigit = true
            }
            // If all conditions are met, return true
            if (hasLowercase && hasUppercase && hasDigit) return true
        }

        // Return false if any condition is not met
        return false
    }
}