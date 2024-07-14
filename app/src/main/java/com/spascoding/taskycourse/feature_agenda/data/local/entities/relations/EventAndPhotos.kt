package com.spascoding.taskycourse.feature_agenda.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.spascoding.taskycourse.feature_agenda.data.local.entities.Event
import com.spascoding.taskycourse.feature_agenda.data.local.entities.Photo

data class EventAndPhotos(
    @Embedded val event: Event,
    @Relation(
        parentColumn = "id",
        entityColumn = "eventId"
    )
    val photos: List<Photo>
)
