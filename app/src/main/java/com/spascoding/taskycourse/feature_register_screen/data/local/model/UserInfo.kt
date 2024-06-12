package com.spascoding.taskycourse.feature_register_screen.data.local.model

data class UserInfo(
    val email: String,
    val password: String,
    val accessToken: String,
    val refreshToken: String,
    val fullName: String,
    val userId: String,
    val accessTokenExpirationTimestamp: Long,
)