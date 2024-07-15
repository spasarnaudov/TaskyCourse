package com.spascoding.taskycourse.feature_agenda.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "events")
@TypeConverters(Converters::class)
data class Event(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val from: Long,
    val to: Long,
    val remindAt: Long,
    val host: String,
    val isUserEventCreator: Boolean,
    val photos: List<String>?
)
