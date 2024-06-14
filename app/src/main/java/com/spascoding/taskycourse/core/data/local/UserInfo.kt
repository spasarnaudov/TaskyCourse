package com.spascoding.taskycourse.core.data.local

data class UserInfo(
    val email: String,
    val password: String,
    val accessToken: String,
    val refreshToken: String,
    val fullName: String,
    val userId: String,
    val accessTokenExpirationTimestamp: Long,
)