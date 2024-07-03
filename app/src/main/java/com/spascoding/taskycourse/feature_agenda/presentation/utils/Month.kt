package com.spascoding.taskycourse.feature_agenda.presentation.utils

import java.util.Calendar

enum class Month(val description: String) {
    JANUARY("JANUARY"),
    FEBRUARY("FEBRUARY"),
    MARCH("MARCH"),
    APRIL("APRIL"),
    MAY("MAY"),
    JUNE("JUNE"),
    JULY("JULY"),
    AUGUST("AUGUST"),
    SEPTEMBER("SEPTEMBER"),
    OCTOBER("OCTOBER"),
    NOVEMBER("NOVEMBER"),
    DECEMBER("DECEMBER");

    companion object {
        fun get(
            current: Int = Calendar.getInstance().get(Calendar.MONTH)
        ): Month {
            return when(current) {
                1 -> FEBRUARY
                2 -> MARCH
                3 -> APRIL
                4 -> MAY
                5 -> JUNE
                6 -> JULY
                7 -> AUGUST
                8 -> SEPTEMBER
                9 -> OCTOBER
                10 -> NOVEMBER
                11 -> DECEMBER
                else -> JANUARY
            }
        }
    }

}