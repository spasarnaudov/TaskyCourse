package com.spascoding.taskycourse.feature_auth.presentation.register

data class RegisterViewModelState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
)