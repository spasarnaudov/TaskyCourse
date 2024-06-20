package com.spascoding.taskycourse.feature_auth.data.repository

import com.spascoding.taskycourse.core.data.Result
import com.spascoding.taskycourse.core.data.local.UserInfo
import com.spascoding.taskycourse.core.data.local.UserInfoManager
import com.spascoding.taskycourse.core.data.util.RequestHelper
import com.spascoding.taskycourse.core.domain.DataError
import com.spascoding.taskycourse.feature_auth.data.remote.AuthApi
import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginRequest
import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginResponse
import com.spascoding.taskycourse.feature_auth.domain.repository.AuthRepository
import com.spascoding.taskycourse.feature_auth.data.remote.model.RegisterRequest
import kotlinx.coroutines.flow.first
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authenticationApi: AuthApi,
    private val userInfoManager: UserInfoManager,
) : AuthRepository {

    override suspend fun register(
        name: String,
        email: String,
        password: String,
    ): Result<Unit?, DataError.Remote> {
        val request = RegisterRequest(name, email, password)
        return RequestHelper.performRequest(
            request = { authenticationApi.register(request) }
        )
    }

    override suspend fun login(
        email: String,
        password: String,
    ): Result<LoginResponse?, DataError.Remote> {
        val request = LoginRequest(email, password)
        return RequestHelper.performRequest(
            request = { authenticationApi.login(request) },
            onSuccess = { loginResponse ->
                if (loginResponse == null) {
                    return@performRequest
                }
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
        )
    }

//    override suspend fun authenticate(): Boolean {
//        val userInfo = userInfoManager.userInfoFlow.first() ?: return false
//        val response = authenticationApi.authenticate(userInfo.accessToken)
//        return response.isSuccessful
//    }

}