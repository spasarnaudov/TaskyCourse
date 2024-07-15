package com.spascoding.taskycourse.feature_agenda.di

import android.content.Context
import androidx.room.Room
import com.spascoding.taskycourse.core.data.local.UserInfoManager
import com.spascoding.taskycourse.feature_agenda.data.local.daos.AgendaDao
import com.spascoding.taskycourse.feature_agenda.data.local.TaskyDatabase
import com.spascoding.taskycourse.feature_agenda.data.remote.AgendaApi
import com.spascoding.taskycourse.feature_agenda.data.repository.AgendaRepositoryImpl
import com.spascoding.taskycourse.feature_agenda.domain.repository.AgendaRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        dao: AgendaDao,
    ): AgendaRepository {
        return AgendaRepositoryImpl(agendaApi, userInfoManager, dao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskyDatabase {
        return Room.databaseBuilder(
            context,
            TaskyDatabase::class.java, "tasky-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAgendaDao(database: TaskyDatabase): AgendaDao {
        return database.agendaDao
    }

}