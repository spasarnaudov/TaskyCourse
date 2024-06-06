package com.spascoding.taskycourse.feature_register_screen.data.remote.model

data class RefreshTokenResponse(
    val accessToken: String,
    val expirationTimestamp: Long,
)