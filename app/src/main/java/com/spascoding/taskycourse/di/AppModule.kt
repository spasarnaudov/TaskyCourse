package com.spascoding.taskycourse.di

import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticateUserUseCase
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticationUseCases
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.LoginUserUseCase
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.LogoutUserUseCase
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.RegisterUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthenticationUseCases(
        authenticateUser: AuthenticateUserUseCase,
        loginUser: LoginUserUseCase,
        logoutUser: LogoutUserUseCase,
        registerUser: RegisterUserUseCase,
    ): AuthenticationUseCases {
        return AuthenticationUseCases(
            authenticateUser,
            loginUser,
            logoutUser,
            registerUser,
        )
    }

}