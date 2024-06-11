package com.spascoding.taskycourse.feature_register_screen.data.remote.model

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String
)