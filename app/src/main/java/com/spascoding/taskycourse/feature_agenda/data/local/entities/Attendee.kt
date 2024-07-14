package com.spascoding.taskycourse.feature_agenda.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Attendee(
    @PrimaryKey(autoGenerate = false)
    val email: String,
    val fullName: String,
    val userId: String,
    val isGoing: Boolean,
    val remindAt: Long
)