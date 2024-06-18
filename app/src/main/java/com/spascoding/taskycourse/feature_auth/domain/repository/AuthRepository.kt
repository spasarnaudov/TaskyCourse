package com.spascoding.taskycourse.feature_auth.domain.repository

import com.spascoding.taskycourse.feature_auth.data.remote.model.LoginResponse
import com.spascoding.taskycourse.core.data.Result
import com.spascoding.taskycourse.core.domain.DataError

interface AuthRepository {
    suspend fun register(name: String, email: String, password: String): Result<Unit?, DataError.Remote>
    suspend fun login(email: String, password: String): Result<LoginResponse?, DataError.Remote>
    suspend fun authenticate(): Boolean
}