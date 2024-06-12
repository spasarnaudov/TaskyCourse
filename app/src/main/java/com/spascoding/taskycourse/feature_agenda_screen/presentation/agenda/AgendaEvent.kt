package com.spascoding.taskycourse.feature_agenda_screen.presentation.agenda

sealed interface AgendaEvent {
    data object LogoutAction : AgendaEvent
}