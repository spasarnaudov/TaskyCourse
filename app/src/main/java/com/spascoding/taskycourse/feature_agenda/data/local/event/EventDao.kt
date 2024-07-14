package com.spascoding.taskycourse.feature_agenda.data.local.event

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getAllEvents(): LiveData<List<Event>>

    @Upsert
    suspend fun upsert(event: Event)

    @Delete
    suspend fun delete(event: Event)
}