package com.spascoding.taskycourse.feature_register_screen.domain.repository

import com.spascoding.taskycourse.feature_register_screen.data.remote.model.LoginResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun register(
        name: String,
        email: String,
        password: String,
    ): Response<Void>
    suspend fun login(
        email: String,
        password: String,
    ): Response<LoginResponse>
    fun authenticate()
    fun logout()
}