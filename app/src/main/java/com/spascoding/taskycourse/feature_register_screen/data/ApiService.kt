package com.spascoding.taskycourse.feature_register_screen.data

import com.spascoding.taskycourse.feature_register_screen.data.model.LoginRequest
import com.spascoding.taskycourse.feature_register_screen.data.model.LoginResponse
import com.spascoding.taskycourse.feature_register_screen.data.model.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("register")
    fun register(
        @Body body: RegisterRequest
    ): Call<Void>

    @POST("login")
    fun login(
        @Body body: LoginRequest
    ): Call<LoginResponse>

    @GET("authenticate")
    fun authenticate(
        @Header("Authorization") token: String
    ): Call<Void>

    @GET("logout")
    fun logout(
        @Header("Authorization") token: String
    ): Call<Void>
}