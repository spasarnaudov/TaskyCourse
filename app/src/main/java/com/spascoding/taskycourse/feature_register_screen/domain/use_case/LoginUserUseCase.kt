package com.spascoding.taskycourse.feature_register_screen.domain.use_case

import com.spascoding.taskycourse.feature_register_screen.data.remote.model.LoginResponse
import com.spascoding.taskycourse.feature_register_screen.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String,
    ): Response<LoginResponse> {
        return authRepository.login(email, password)
    }

}