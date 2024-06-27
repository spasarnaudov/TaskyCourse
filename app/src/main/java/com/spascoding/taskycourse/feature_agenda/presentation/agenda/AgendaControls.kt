package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.spascoding.taskycourse.core.constants.Padding

@Composable
fun AgendaControls(
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit
) {
    Row {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(
                    start = Padding.MEDIUM,
                    top = Padding.LARGE,
                    end = Padding.MEDIUM,
                ),
            onClick = {
                onEvent(AgendaEvent.LogoutAction)
            }
        ) {
            Text(
                text = state.username,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}