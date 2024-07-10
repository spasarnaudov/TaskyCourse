package com.spascoding.taskycourse.feature_agenda.data.local.reminder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
data class Reminder(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val remindAt: Long
)