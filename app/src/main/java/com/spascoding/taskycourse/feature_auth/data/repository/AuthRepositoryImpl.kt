package com.spascoding.taskycourse.feature_auth.data.repository

import com.spascoding.taskycourse.core.Result
import com.spascoding.taskycourse.core.data.local.UserInfo
import com.spascoding.taskycourse.core.data.local.UserInfoManager
import com.spascoding.taskycourse.feature_auth.data.remote.AuthenticationApi
import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginRequest
import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginResponse
import com.spascoding.taskycourse.feature_auth.data.remote.model.RegisterRequest
import com.spascoding.taskycourse.feature_auth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authenticationApi: AuthenticationApi,
    private val userInfoManager: UserInfoManager,
) : AuthRepository {

    override suspend fun register(
        name: String,
        email: String,
        password: String,
    ): Result<Void, Error> {
        val request = RegisterRequest(name, email, password)
        val response = authenticationApi.register(request)
        return Result.Success(response.body()!!)
    }

    override suspend fun login(
        email: String,
        password: String,
    ): Result<LoginResponse, Error> {
        val request = LoginRequest(email, password)
        val response = authenticationApi.login(request)
        if (response.isSuccessful) {
            val loginResponse = response.body() as LoginResponse
            val userInfo = UserInfo(
                email = email,
                password = password,
                accessToken = loginResponse.accessToken,
                refreshToken = loginResponse.refreshToken,
                fullName = loginResponse.fullName,
                userId = loginResponse.userId,
                accessTokenExpirationTimestamp = loginResponse.accessTokenExpirationTimestamp,
            )
            userInfoManager.saveUserInfo(userInfo)
        }
        return Result.Success(response.body()!!)
    }

    override suspend fun authenticate(): Boolean {
        val userInfo = userInfoManager.userInfoFlow.first() ?: return false
        val response = authenticationApi.authenticate(userInfo.accessToken)
        return response.isSuccessful
    }

}