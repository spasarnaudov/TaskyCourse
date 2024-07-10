package com.spascoding.taskycourse.feature_agenda.data.local.task

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val dueDate: Long,
    val isCompleted: Boolean
)