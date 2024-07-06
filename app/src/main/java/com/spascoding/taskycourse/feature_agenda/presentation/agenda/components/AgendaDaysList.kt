package com.spascoding.taskycourse.feature_agenda.presentation.agenda.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spascoding.taskycourse.core.constants.Colors
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.core.constants.RoundCorner
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.AgendaEvent
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.AgendaViewModelState
import com.spascoding.taskycourse.feature_agenda.presentation.utils.DATE_FORMAT
import java.time.LocalDate

@Composable
fun AgendaDaysList(
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Padding.MEDIUM)
        ) {
            var date = state.calendarDate
            for (i in 1..6) {
                DayElement(
                    state,
                    onEvent,
                    modifier = Modifier.weight(1f),
                    date,
                )
                date = date.plusDays(1)
            }
        }
        Text(
            modifier = Modifier.padding(Padding.MEDIUM),
            text = state.selectedDate.format(DATE_FORMAT),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = Colors.Black,
        )
    }
}

@Composable
fun DayElement(
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit,
    modifier: Modifier,
    date: LocalDate,
) {
    val isSelectedDay = state.selectedDate == date
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .height(60.dp)
                .width(40.dp)
                .background(
                    if (isSelectedDay) Colors.Orange else Color.White,
                    RoundedCornerShape(RoundCorner.LARGE)
                )
                .clip(RoundedCornerShape(RoundCorner.LARGE))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(
                        color = Color.Black
                    ),
                    enabled = true,
                    onClick = {
                        onEvent.invoke(AgendaEvent.SelectDateAction(date))
                    },
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .wrapContentHeight(),
                text = date.dayOfWeek.name[0].uppercase(),
                color = Colors.Gray,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .wrapContentHeight(),
                text = date.dayOfMonth.toString(),
                color = Colors.DarkGray,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        }
    }
}