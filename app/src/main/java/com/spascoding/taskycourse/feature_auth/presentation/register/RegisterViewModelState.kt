package com.spascoding.taskycourse.feature_auth.presentation.register

data class RegisterViewModelState(
    val name: String = "",
    val validName: Boolean = false,
    val email: String = "",
    val validEmail: Boolean = false,
    val password: String = "",
    val validPassword: Boolean = false,
)