package com.spascoding.taskycourse.feature_auth.data.remote.model

data class RegisterRequest(
    val fullName: String,
    val email: String,
    val password: String
)