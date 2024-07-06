package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import java.time.LocalDate

data class AgendaViewModelState(
    val username: String = "",
    val calendarDate: LocalDate = LocalDate.now(),
    val selectedDate: LocalDate = calendarDate,
)