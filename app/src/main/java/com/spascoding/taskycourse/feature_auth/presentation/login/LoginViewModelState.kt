package com.spascoding.taskycourse.feature_auth.presentation.login

data class LoginViewModelState(
    val email: String = "",
    val validEmail: Boolean = false,
    val password: String = "",
    val validPassword: Boolean = false,
)