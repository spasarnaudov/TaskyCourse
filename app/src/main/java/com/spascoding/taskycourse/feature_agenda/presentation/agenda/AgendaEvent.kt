package com.spascoding.taskycourse.feature_agenda.presentation.agenda

sealed interface AgendaEvent {
    data object LogoutAction : AgendaEvent
}