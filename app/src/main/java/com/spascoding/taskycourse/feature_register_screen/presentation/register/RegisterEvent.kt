package com.spascoding.taskycourse.feature_register_screen.presentation.register

sealed interface RegisterEvent {
    data class ChangeName(val name: String) : RegisterEvent
    data class ChangeEmailAddress(val email: String) : RegisterEvent
    data class ChangePassword(val password: String) : RegisterEvent
    class RegisterAction(val onSuccess: (String) -> Unit) : RegisterEvent
    data object BackAction : RegisterEvent
}