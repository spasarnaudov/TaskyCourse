package com.spascoding.taskycourse.feature_register_screen.domain.repository

import com.spascoding.taskycourse.feature_register_screen.data.remote.model.LoginResponse
import retrofit2.Response

interface AuthRepository {
    fun register()
    suspend fun login(
        email: String,
        password: String,
    ): Response<LoginResponse>
    fun authenticate()
    fun logout()
}