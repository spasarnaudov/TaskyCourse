package com.spascoding.taskycourse.feature_auth.data.remote.model

data class RefreshTokenResponse(
    val accessToken: String,
    val expirationTimestamp: Long,
)