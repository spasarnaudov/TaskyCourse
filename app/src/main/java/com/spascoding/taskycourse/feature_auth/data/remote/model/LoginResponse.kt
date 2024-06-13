package com.spascoding.taskycourse.feature_auth.data.remote.model

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val fullName: String,
    val userId: String,
    val accessTokenExpirationTimestamp: Long,
)