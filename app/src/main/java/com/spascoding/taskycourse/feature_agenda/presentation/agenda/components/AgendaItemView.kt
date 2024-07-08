package com.spascoding.taskycourse.feature_agenda.presentation.agenda.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.core.constants.RoundCorner
import com.spascoding.taskycourse.feature_agenda.domain.agenda.model.AgendaItem
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.AgendaEvent
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.AgendaViewModelState
import com.spascoding.taskycourse.feature_agenda.presentation.utils.DATE_FORMAT

@Composable
fun AgendaItemView(
    agendaItem: AgendaItem,
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Padding.SMALL)
            .background(
                color = Color.LightGray,
                shape = RoundedCornerShape(RoundCorner.MEDIUM)
            )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            RadioButton(selected = agendaItem.done, onClick = { onEvent(AgendaEvent.SelectAgendaDone) })
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Padding.MEDIUM)
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = agendaItem.title
                    )
                    Icon(
                        modifier = Modifier
                            .clickable(
                                indication = rememberRipple(color = Color.Black),
                                interactionSource = remember { MutableInteractionSource() },
                            ) { onEvent(AgendaEvent.SelectAgendaItemMenu) },
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = null,
                    )
                }
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Padding.MEDIUM),
                    text = agendaItem.description
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Padding.MEDIUM),
                    text = if (agendaItem.to != null) {
                        "${agendaItem.from.format(DATE_FORMAT)} - ${agendaItem.to.format(DATE_FORMAT)}"
                    } else {
                        agendaItem.from.format(DATE_FORMAT)
                    },
                    textAlign = TextAlign.End
                )
            }
        }
    }
}