package com.spascoding.taskycourse.feature_agenda.domain.repository

import com.spascoding.taskycourse.core.data.Result
import com.spascoding.taskycourse.core.domain.DataError

interface AgendaRepository {
    suspend fun logout(): Result<Unit?, DataError.Remote>
}