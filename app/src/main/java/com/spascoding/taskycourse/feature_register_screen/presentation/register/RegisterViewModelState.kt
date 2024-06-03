package com.spascoding.taskycourse.feature_register_screen.presentation.register

data class RegisterViewModelState(
    val name: String = "",
    val emailAddress: String = "",
    val password: String = "",
)