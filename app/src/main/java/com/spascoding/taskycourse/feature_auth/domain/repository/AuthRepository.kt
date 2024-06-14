package com.spascoding.taskycourse.feature_auth.domain.repository

import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginResponse
import com.spascoding.taskycourse.core.data.Result

interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): Result<Void, Error>
    suspend fun login(email: String, password: String): Result<LoginResponse, Error>
    suspend fun authenticate(): Boolean
}