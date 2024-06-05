package com.spascoding.taskycourse.feature_register_screen.data.model

data class LoginResponse(
    val token: String,
    val userId: String,
    val fullName: String
)