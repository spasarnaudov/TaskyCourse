package com.spascoding.taskycourse.feature_auth.data.repository

import com.spascoding.taskycourse.core.data.Result
import com.spascoding.taskycourse.core.domain.DataError
import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginResponse
import com.spascoding.taskycourse.feature_auth.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {

    private var registerSuccess = true // Simulate success by default

    fun stubRegisterSuccess() {
        registerSuccess = true
    }

    fun stubRegisterFailure() {
        registerSuccess = false
    }

    override suspend fun register(
        name: String,
        email: String,
        password: String
    ): Result<Unit?, DataError.Remote> {
        return if (registerSuccess) {
            Result.Success(Unit)
        } else {
            Result.Error(DataError.Remote.UNKNOWN)
        }
    }

    override suspend fun login(
        email: String,
        password: String
    ): Result<LoginResponse?, DataError.Remote> {
        // Simulate login success if needed for tests
        val loginResponse = LoginResponse(
            accessToken = "",
            refreshToken = "",
            fullName = "",
            userId = "",
            accessTokenExpirationTimestamp = 0L,
        )
        return Result.Success(loginResponse)
    }

}