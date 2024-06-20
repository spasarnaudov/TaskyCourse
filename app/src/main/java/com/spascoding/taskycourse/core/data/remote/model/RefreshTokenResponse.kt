package com.spascoding.taskycourse.core.data.remote.model

data class RefreshTokenResponse(
    val accessToken: String,
    val expirationTimestamp: Long,
)