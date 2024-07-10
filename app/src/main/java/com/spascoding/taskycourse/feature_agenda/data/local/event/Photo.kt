package com.spascoding.taskycourse.feature_agenda.data.local.event

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo(
    @PrimaryKey val key: String,
    val url: String
)