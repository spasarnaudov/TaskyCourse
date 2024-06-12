package com.spascoding.taskycourse.di

import android.content.Context
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfoManager
import com.spascoding.taskycourse.feature_register_screen.data.remote.AuthenticationApi
import com.spascoding.taskycourse.feature_register_screen.data.remote.TaskyClient
import com.spascoding.taskycourse.feature_register_screen.data.repository.AuthRepositoryImpl
import com.spascoding.taskycourse.feature_register_screen.domain.repository.AuthRepository
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
    fun provideAuthenticationApi(): AuthenticationApi {
        return TaskyClient.retrofit.create(AuthenticationApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserInfoManager(@ApplicationContext context: Context): UserInfoManager {
        return UserInfoManager(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authenticationApi: AuthenticationApi,
        userInfoManager: UserInfoManager,
    ): AuthRepository {
        return AuthRepositoryImpl(authenticationApi, userInfoManager)
    }

}