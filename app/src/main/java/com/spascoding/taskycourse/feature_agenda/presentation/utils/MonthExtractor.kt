package com.spascoding.taskycourse.feature_agenda.presentation.utils

class MonthExtractor(private val date: String) {

    fun extract(): Int {
        return date.split("-")[1].toInt() - 1 //month is zero based
    }

}