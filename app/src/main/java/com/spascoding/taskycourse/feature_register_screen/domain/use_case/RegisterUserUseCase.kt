package com.spascoding.taskycourse.feature_register_screen.domain.use_case

import com.spascoding.taskycourse.feature_register_screen.domain.repository.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke(
        name: String,
        email: String,
        password: String,
    ): Response<Void> {
        return authRepository.register(name, email, password)
    }

}