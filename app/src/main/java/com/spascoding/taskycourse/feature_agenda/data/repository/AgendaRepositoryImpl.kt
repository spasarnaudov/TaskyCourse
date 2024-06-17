package com.spascoding.taskycourse.feature_agenda.data.repository

import com.spascoding.taskycourse.core.data.Result
import com.spascoding.taskycourse.core.data.local.UserInfoManager
import com.spascoding.taskycourse.core.data.util.RequestHelper
import com.spascoding.taskycourse.core.domain.DataError
import com.spascoding.taskycourse.feature_agenda.data.data.remote.AgendaApi
import com.spascoding.taskycourse.feature_agenda.domain.repository.AgendaRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val agendaApi: AgendaApi,
    private val userInfoManager: UserInfoManager,
) : AgendaRepository {

    override suspend fun logout(): Result<Unit?, DataError.Remote> {
        return RequestHelper.performRequest(
            request = { agendaApi.logout() },
            onSuccess = { userInfoManager.clearUserInfo() }
        )
    }
}