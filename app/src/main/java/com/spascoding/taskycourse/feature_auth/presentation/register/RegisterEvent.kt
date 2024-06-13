package com.spascoding.taskycourse.feature_auth.presentation.register

sealed interface RegisterEvent {
    data class ChangeName(val name: String) : RegisterEvent
    data class ChangeEmailAddress(val email: String) : RegisterEvent
    data class ChangePassword(val password: String) : RegisterEvent
    data object RegisterAction : RegisterEvent
    data object BackAction : RegisterEvent
}