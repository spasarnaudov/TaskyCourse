package com.spascoding.taskycourse.feature_auth.data.remote.model

data class RefreshTokenRequest(
    val refreshToken: String,
    val userId: String,
)