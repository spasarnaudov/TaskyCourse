package com.spascoding.taskycourse.feature_auth.data.remote.model

data class LoginRequest(
    val email: String,
    val password: String
)