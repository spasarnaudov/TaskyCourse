package com.spascoding.taskycourse.feature_register_screen.domain.use_case

import javax.inject.Inject

const val TAG = "AuthenticationUseCases"

data class AuthenticationUseCases @Inject constructor(
    val authenticateUser: AuthenticateUserUseCase,
    val logoutUser: LogoutUserUseCase,
) {

}