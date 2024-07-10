package com.spascoding.taskycourse.feature_agenda.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.spascoding.taskycourse.feature_agenda.data.local.event.Event
import com.spascoding.taskycourse.feature_agenda.data.local.event.Photo
import com.spascoding.taskycourse.feature_agenda.data.local.reminder.Reminder
import com.spascoding.taskycourse.feature_agenda.data.local.task.Task
import com.spascoding.taskycourse.feature_agenda.data.utils.Converters

@Database(
    entities = [Event::class, Photo::class, Task::class, Reminder::class],
    version = 1,
)
@TypeConverters(Converters::class)
abstract class AgendaDatabase: RoomDatabase() {
    abstract val dao: AgendaDao
}