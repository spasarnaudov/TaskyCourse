package com.spascoding.taskycourse.feature_auth.presentation.util

import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.presentation.UiText
import com.spascoding.taskycourse.feature_auth.domain.util.UserDataValidator

fun UserDataValidator.NameError.asUiText(): UiText {
    val error = when (this) {
        UserDataValidator.NameError.TOO_SHORT -> R.string.name_too_short
        UserDataValidator.NameError.TOO_LONG -> R.string.name_too_long
    }
    return UiText.StringResource(error)
}

fun UserDataValidator.EmailError.asUiText(): UiText {
    val error = when (this) {
        UserDataValidator.EmailError.INVALID_EMAIL -> R.string.email_invalid
    }
    return UiText.StringResource(error)
}

fun UserDataValidator.PasswordError.asUiText(): UiText {
    val error = when (this) {
        UserDataValidator.PasswordError.TOO_SHORT -> R.string.password_too_short
        UserDataValidator.PasswordError.NO_LOWERCASE -> R.string.password_no_lowercase
        UserDataValidator.PasswordError.NO_UPPERCASE -> R.string.password_no_uppercase
        UserDataValidator.PasswordError.NO_DIGIT -> R.string.password_no_digit
    }
    return UiText.StringResource(error)
}