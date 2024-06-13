package com.spascoding.taskycourse.feature_auth.data.remote

import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginRequest
import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginResponse
import com.spascoding.taskycourse.feature_auth.data.remote.model.RefreshTokenRequest
import com.spascoding.taskycourse.feature_auth.data.remote.model.RefreshTokenResponse
import com.spascoding.taskycourse.feature_auth.data.remote.model.RegisterRequest
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("/register")
    suspend fun register(
        @Body body: RegisterRequest
    ): Response<Void>

    @POST("/login")
    suspend fun login(
        @Body body: LoginRequest
    ): Response<LoginResponse>

    @POST("/accessToken")
    fun refreshAccessToken(@Body request: RefreshTokenRequest): Call<RefreshTokenResponse>

    @GET("/authenticate")
    suspend fun authenticate(
        @Header("Authorization") token: String
    ): Response<Void>

    @GET("/logout")
    suspend fun logout(
        @Header("Authorization") token: String
    ): Response<Void>
}