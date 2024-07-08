package com.spascoding.taskycourse.feature_agenda.domain.agenda.model

import java.time.LocalDate

data class AgendaItem(
    val title: String,
    val description: String,
    val from: LocalDate,
    val to: LocalDate? = null,
    val done: Boolean,
)