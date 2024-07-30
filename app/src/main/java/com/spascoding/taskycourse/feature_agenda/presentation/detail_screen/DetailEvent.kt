package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen

sealed interface DetailEvent {
    data object CloseAction : DetailEvent
    data object SaveAction : DetailEvent
    data object EditAction : DetailEvent
    data object DeleteAction : DetailEvent
    data object PopBackStack : DetailEvent
    data object EditTitleClick : DetailEvent
    data object EditDescriptionClick : DetailEvent
}