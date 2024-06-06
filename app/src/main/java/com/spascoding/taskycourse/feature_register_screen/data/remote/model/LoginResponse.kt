package com.spascoding.taskycourse.feature_register_screen.data.remote.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val fullName: String,
    val userId: String,
    val accessTokenExpirationTimestamp: Long,
)