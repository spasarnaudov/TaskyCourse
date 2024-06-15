package com.spascoding.taskycourse.feature_auth.presentation.register

sealed interface RegisterEvent {
    data class ChangeName(val name: String) : RegisterEvent
    data class ChangeEmail(val email: String) : RegisterEvent
    data class ChangePassword(val password: String) : RegisterEvent
    data class RegisterAction(val name: String, val email: String, val password: String) : RegisterEvent
    data object BackAction : RegisterEvent
}