package com.spascoding.taskycourse.feature_agenda.presentation.event

import java.time.LocalDate

sealed interface EventEvent {
    data object LogoutAction : EventEvent
    data class SelectCalendarDateAction(val date: LocalDate) : EventEvent
    data class SelectDateAction(val date: LocalDate) : EventEvent
    data object SelectAgendaItemMenu : EventEvent
    data object SelectAgendaDone : EventEvent
}