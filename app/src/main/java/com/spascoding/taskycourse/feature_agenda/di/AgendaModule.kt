package com.spascoding.taskycourse.feature_agenda.di

import com.spascoding.taskycourse.core.data.local.UserInfoManager
import com.spascoding.taskycourse.feature_agenda.data.data.remote.AgendaApi
import com.spascoding.taskycourse.feature_agenda.data.repository.AgendaRepositoryImpl
import com.spascoding.taskycourse.feature_agenda.domain.repository.AgendaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AgendaModule {

    @Provides
    @Singleton
    fun provideAgendaApi(retrofit: Retrofit): AgendaApi {
        return retrofit.create(AgendaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAgendaRepository(
        agendaApi: AgendaApi,
        userInfoManager: UserInfoManager,
    ): AgendaRepository {
        return AgendaRepositoryImpl(agendaApi, userInfoManager)
    }

}