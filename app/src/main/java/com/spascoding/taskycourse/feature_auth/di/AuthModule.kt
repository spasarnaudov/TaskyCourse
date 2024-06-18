package com.spascoding.taskycourse.feature_auth.di

import com.spascoding.taskycourse.core.data.local.UserInfoManager
import com.spascoding.taskycourse.feature_auth.data.remote.AuthApi
import com.spascoding.taskycourse.feature_auth.data.repository.AuthRepositoryImpl
import com.spascoding.taskycourse.feature_auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authenticationApi: AuthApi,
        userInfoManager: UserInfoManager,
    ): AuthRepository {
        return AuthRepositoryImpl(authenticationApi, userInfoManager)
    }

}