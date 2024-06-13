package com.spascoding.taskycourse.feature_auth.data.local.model

data class UserInfo(
    val email: String,
    val password: String,
    val accessToken: String,
    val refreshToken: String,
    val fullName: String,
    val userId: String,
    val accessTokenExpirationTimestamp: Long,
)