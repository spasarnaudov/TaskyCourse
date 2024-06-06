package com.spascoding.taskycourse.feature_register_screen.data.remote

import com.spascoding.taskycourse.feature_register_screen.data.remote.model.LoginRequest
import com.spascoding.taskycourse.feature_register_screen.data.remote.model.LoginResponse
import com.spascoding.taskycourse.feature_register_screen.data.remote.model.RefreshTokenRequest
import com.spascoding.taskycourse.feature_register_screen.data.remote.model.RefreshTokenResponse
import com.spascoding.taskycourse.feature_register_screen.data.remote.model.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthenticationApi {
    @POST("/register")
    fun register(
        @Body body: RegisterRequest
    ): Call<Void>

    @POST("/login")
    fun login(
        @Body body: LoginRequest
    ): Call<LoginResponse>

    @POST("/accessToken")
    fun refreshAccessToken(@Body request: RefreshTokenRequest): Call<RefreshTokenResponse>

    @GET("/authenticate")
    fun authenticate(
        @Header("Authorization") token: String
    ): Call<Void>

    @GET("/logout")
    fun logout(
        @Header("Authorization") token: String
    ): Call<Void>
}