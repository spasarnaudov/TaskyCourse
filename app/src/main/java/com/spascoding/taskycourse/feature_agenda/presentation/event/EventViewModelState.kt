package com.spascoding.taskycourse.feature_agenda.presentation.event

import com.spascoding.taskycourse.feature_agenda.domain.agenda.model.AgendaItem
import java.time.LocalDate

data class EventViewModelState(
    val username: String = "",
    val calendarDate: LocalDate = LocalDate.now(),
    val selectedDate: LocalDate = calendarDate,
    val agendaItems: List<AgendaItem> = listOf(),
)