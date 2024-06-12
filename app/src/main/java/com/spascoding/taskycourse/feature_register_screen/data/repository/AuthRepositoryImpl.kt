package com.spascoding.taskycourse.feature_register_screen.data.repository

import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfo
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfoManager
import com.spascoding.taskycourse.feature_register_screen.data.remote.AuthenticationApi
import com.spascoding.taskycourse.feature_register_screen.data.remote.model.LoginRequest
import com.spascoding.taskycourse.feature_register_screen.data.remote.model.LoginResponse
import com.spascoding.taskycourse.feature_register_screen.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authenticationApi: AuthenticationApi,
    private val userInfoManager: UserInfoManager,
) : AuthRepository {
    override fun register() {
        TODO("Not yet implemented")
    }

    override suspend fun login(
        email: String,
        password: String,
    ): Response<LoginResponse> {
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
        return response
    }

    override fun authenticate() {
        TODO("Not yet implemented")
    }

    override fun logout() {
        TODO("Not yet implemented")
    }
}