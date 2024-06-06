package com.spascoding.taskycourse.feature_register_screen.data.remote.model

data class RefreshTokenRequest(
    val refreshToken: String,
    val userId: String,
)