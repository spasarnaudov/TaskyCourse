package com.spascoding.taskycourse.feature_auth.domain.repository

import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginResponse
import retrofit2.Response

interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): Response<Void>
    suspend fun login(email: String, password: String): Response<LoginResponse>
    suspend fun authenticate(token: String): Boolean
    suspend fun logout(token: String)
}