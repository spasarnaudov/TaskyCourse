package com.spascoding.taskycourse.feature_agenda.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spascoding.taskycourse.feature_agenda.data.local.daos.AgendaDao
import com.spascoding.taskycourse.feature_agenda.data.local.daos.EventDao
import com.spascoding.taskycourse.feature_agenda.data.local.daos.ReminderDao
import com.spascoding.taskycourse.feature_agenda.data.local.daos.TaskDao
import com.spascoding.taskycourse.feature_agenda.data.local.entities.Attendee
import com.spascoding.taskycourse.feature_agenda.data.local.entities.Event
import com.spascoding.taskycourse.feature_agenda.data.local.entities.Reminder
import com.spascoding.taskycourse.feature_agenda.data.local.entities.Task

@Database(
    entities = [
        Event::class,
        Attendee::class,
        Task::class,
        Reminder::class],
    version = 1,
)
abstract class TaskyDatabase: RoomDatabase() {
    abstract val agendaDao: AgendaDao
    abstract val eventDao: EventDao
    abstract val reminderDao: ReminderDao
    abstract val taskDao: TaskDao
}