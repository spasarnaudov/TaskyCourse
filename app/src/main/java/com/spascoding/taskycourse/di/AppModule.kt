package com.spascoding.taskycourse.di

import android.content.Context
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfoManager
import com.spascoding.taskycourse.feature_register_screen.data.remote.AuthenticationApi
import com.spascoding.taskycourse.feature_register_screen.data.remote.TaskyClient
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticateUserUseCase
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticationUseCases
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.LoginUserUseCase
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.LogoutUserUseCase
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.RegisterUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideAuthenticationApi(): AuthenticationApi {
        return TaskyClient.retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserInfoManager(@ApplicationContext context: Context): UserInfoManager {
        return UserInfoManager(context)
    }

}