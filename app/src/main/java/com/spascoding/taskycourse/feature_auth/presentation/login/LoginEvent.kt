package com.spascoding.taskycourse.feature_auth.presentation.login

sealed interface LoginEvent {
    data class ChangeEmail(val email: String) : LoginEvent
    data class ChangePassword(val password: String) : LoginEvent
    data class LoginAction(val email: String, val password: String) : LoginEvent
    data object SignUpAction : LoginEvent
}