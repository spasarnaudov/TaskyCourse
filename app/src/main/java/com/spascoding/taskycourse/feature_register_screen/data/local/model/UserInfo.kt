package com.spascoding.taskycourse.feature_register_screen.data.local.model

var userInfo: UserInfo = UserInfo()

data class UserInfo(
    val email: String = "",
    val password: String = "",
    val accessToken: String = "",
    val refreshToken: String = "",
    val fullName: String = "",
    val userId: String = "",
    val accessTokenExpirationTimestamp: Long = 0L,
)