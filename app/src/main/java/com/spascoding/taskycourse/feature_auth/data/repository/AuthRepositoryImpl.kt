package com.spascoding.taskycourse.feature_auth.data.repository

import com.spascoding.taskycourse.core.data.Result
import com.spascoding.taskycourse.core.data.local.UserInfo
import com.spascoding.taskycourse.core.data.local.UserInfoManager
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
    ): Result<Void, DataError.Remote> {
        val request = RegisterRequest(name, email, password)
        return performRequest(
            request = { authenticationApi.register(request) }
        )
    }

    override suspend fun login(
        email: String,
        password: String,
    ): Result<LoginResponse, DataError.Remote> {
        val request = LoginRequest(email, password)
        return performRequest(
            request = { authenticationApi.login(request) },
            onSuccess = { loginResponse ->
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

    override suspend fun authenticate(): Boolean {
        val userInfo = userInfoManager.userInfoFlow.first() ?: return false
        val response = authenticationApi.authenticate(userInfo.accessToken)
        return response.isSuccessful
    }

    //Utils

    private suspend fun <T> performRequest(
        request: suspend () -> Response<T>,
        onSuccess: suspend (T) -> Unit = {}
    ): Result<T, DataError.Remote> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                response.body()?.let {
                    onSuccess(it)
                    Result.Success(it)
                } ?: Result.Error(DataError.Remote.UNKNOWN)
            } else {
                //How I can return error massage from server and it needs to do this?
//                val errorResponse = response.errorBody()?.string()
//                val errorMessage = handleErrorMassage(errorResponse)
//                Result.Error(TextError(errorMessage))
                Result.Error(DataError.Remote.UNKNOWN)
            }
        } catch (e: HttpException) {
            handleErrorCode(e.code())
        } catch (e: IOException) {
            Result.Error(DataError.Remote.NO_INTERNET)
        }
    }

    private fun handleErrorMassage(errorResponse: String?): String {
        return if (errorResponse != null) {
            try {
                val jsonObject = JSONObject(errorResponse)
                jsonObject.getString("message")
            } catch (e: Exception) {
                "Unknown error"
            }
        } else {
            "Unknown error"
        }
    }

    private fun handleErrorCode(code: Int): Result.Error<DataError.Remote> {
        return when (code) {
            400 -> Result.Error(DataError.Remote.BAR_REQUEST)
            401 -> Result.Error(DataError.Remote.UNAUTHORIZED)
            403 -> Result.Error(DataError.Remote.FORBIDDEN)
            404 -> Result.Error(DataError.Remote.NOT_FOUND)
            405 -> Result.Error(DataError.Remote.METHOD_NOT_ALLOWED)
            408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
            413 -> Result.Error(DataError.Remote.PAYLOAD_TOO_LARGE)

            500 -> Result.Error(DataError.Remote.INTERNAL_SERVER_ERROR)
            502 -> Result.Error(DataError.Remote.BAD_GATEWAY)
            503 -> Result.Error(DataError.Remote.SERVICE_UNAVAILABLE)
            504 -> Result.Error(DataError.Remote.GATEWAY_TIMEOUT)

            else -> Result.Error(DataError.Remote.UNKNOWN)
        }
    }

}