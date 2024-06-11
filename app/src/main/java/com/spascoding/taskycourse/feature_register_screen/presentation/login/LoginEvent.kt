package com.spascoding.taskycourse.feature_register_screen.presentation.login

sealed interface LoginEvent {
    data class ChangeEmail(val email: String) : LoginEvent
    data class ChangePassword(val password: String) : LoginEvent
    data object LoginAction : LoginEvent
    data object SignUpAction : LoginEvent
}