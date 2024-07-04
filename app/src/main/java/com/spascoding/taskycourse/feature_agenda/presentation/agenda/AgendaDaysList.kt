package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.spascoding.taskycourse.core.constants.Colors
import com.spascoding.taskycourse.core.constants.Padding

@Composable
fun AgendaDaysList(
    state: AgendaViewModelState,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Padding.MEDIUM)
    ) {
        var date = state.date
        for (i in 1..6) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = date.dayOfWeek.name[0].uppercase(),
                    color = Colors.Gray,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = date.dayOfMonth.toString(),
                    color = Colors.DarkGray,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            date = date.plusDays(1)
        }
    }
}