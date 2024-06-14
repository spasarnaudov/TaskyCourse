package com.spascoding.taskycourse.core.di

import android.content.Context
import com.spascoding.taskycourse.core.data.local.UserInfoManager
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
    fun provideUserInfoManager(@ApplicationContext context: Context): UserInfoManager {
        return UserInfoManager(context)
    }

}