package com.spascoding.taskycourse.feature_agenda.data.local.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.spascoding.taskycourse.feature_agenda.data.local.entities.Task

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAllTasks(): LiveData<List<Task>>

    @Upsert
    suspend fun upsert(task: Task)

    @Delete
    suspend fun delete(task: Task)
}