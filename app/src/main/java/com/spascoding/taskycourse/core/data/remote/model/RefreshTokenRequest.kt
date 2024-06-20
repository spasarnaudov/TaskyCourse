package com.spascoding.taskycourse.core.data.remote.model

data class RefreshTokenRequest(
    val refreshToken: String,
    val userId: String,
)