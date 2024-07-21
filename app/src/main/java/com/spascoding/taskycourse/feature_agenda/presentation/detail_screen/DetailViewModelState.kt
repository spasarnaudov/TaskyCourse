package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen

import java.time.LocalDate

data class DetailViewModelState(
    val selectedDate: LocalDate = LocalDate.now(),
    val isEditMode: Boolean = false,
)