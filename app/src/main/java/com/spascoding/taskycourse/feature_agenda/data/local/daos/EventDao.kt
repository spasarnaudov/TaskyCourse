package com.spascoding.taskycourse.feature_agenda.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.spascoding.taskycourse.feature_agenda.data.local.entities.Event

@Dao
interface EventDao {
    @Query("SELECT * FROM events")
    fun getAllEvents(): LiveData<List<Event>>

    @Upsert
    suspend fun upsert(event: Event)

    @Delete
    suspend fun delete(event: Event)
}