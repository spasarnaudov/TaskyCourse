package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import com.spascoding.taskycourse.feature_agenda.presentation.utils.Month

data class AgendaViewModelState(
    val username: String = "",
    val month: String = Month.get().description,
)