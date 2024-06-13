package com.spascoding.taskycourse.feature_agenda.data.repository

import com.spascoding.taskycourse.core.data.local.UserInfoManager
import com.spascoding.taskycourse.feature_agenda.data.data.remote.AgendaApi
import com.spascoding.taskycourse.feature_agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val agendaApi: AgendaApi,
    private val userInfoManager: UserInfoManager,
) : AgendaRepository {

    override suspend fun logout() {
        val userInfo = userInfoManager.userInfoFlow.first()
        if (userInfo != null) {
            val response = agendaApi.logout("Bearer ${userInfo.accessToken}")
            if (response.isSuccessful) {
                userInfoManager.clearUserInfo()
            }
        }
    }
}