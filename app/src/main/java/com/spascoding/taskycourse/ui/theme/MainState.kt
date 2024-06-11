package com.spascoding.taskycourse.ui.theme

import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfo

data class MainState(
    val userInfo: UserInfo,
    val isReady: Boolean,
)