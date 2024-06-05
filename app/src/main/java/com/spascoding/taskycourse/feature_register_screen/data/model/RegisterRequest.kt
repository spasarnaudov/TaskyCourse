package com.spascoding.taskycourse.feature_register_screen.data.model

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String
)