package com.spascoding.taskycourse.feature_auth.data.remote

import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginRequest
import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginResponse
import com.spascoding.taskycourse.core.data.remote.model.RefreshTokenRequest
import com.spascoding.taskycourse.core.data.remote.model.RefreshTokenResponse
import com.spascoding.taskycourse.feature_auth.data.remote.model.RegisterRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {
    @POST("/register")
    suspend fun register(
        @Body body: RegisterRequest
    ): Response<Unit>

    @POST("/login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<LoginResponse>
}