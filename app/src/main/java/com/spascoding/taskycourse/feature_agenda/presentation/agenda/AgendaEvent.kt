package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import java.time.LocalDate

sealed interface AgendaEvent {
    data object LogoutAction : AgendaEvent
    data class SelectDateAction(val date: LocalDate) : AgendaEvent
}