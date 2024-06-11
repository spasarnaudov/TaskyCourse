package com.spascoding.taskycourse.feature_agenda_screen.presentation.agenda

sealed interface AgendaEvent {
    class LogoutAction(val onSuccess: () -> Unit) : AgendaEvent
}