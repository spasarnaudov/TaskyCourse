package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.constants.Colors
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.feature_agenda.presentation.components.DatePicker
import com.spascoding.taskycourse.feature_agenda.presentation.components.defaultDateTimeDialogButtons
import com.vanpra.composematerialdialogs.datetime.date.DatePickerDefaults
import com.vanpra.composematerialdialogs.datetime.date.datepicker

@Composable
fun AgendaControls(
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit
) {
    var mDisplayMenu by remember { mutableStateOf(false) }

    Box {
        DropdownMenu(
            expanded = mDisplayMenu,
            onDismissRequest = { mDisplayMenu = false },
            offset = DpOffset(x = -Padding.MEDIUM, y = 0.dp)
        ) {
            DropdownMenuItem(
                text = { Text(stringResource(R.string.log_out).uppercase()) },
                onClick = { onEvent(AgendaEvent.LogoutAction) },
            )
        }

        Row(
            modifier = Modifier.padding(Padding.MEDIUM),
            verticalAlignment = Alignment.CenterVertically
        ) {
            DatePicker(
                buttonText = state.month,
                buttons = { defaultDateTimeDialogButtons() }
            ) {
                datepicker(colors = DatePickerDefaults.colors(headerBackgroundColor = Color.Black)) {
                    onEvent(AgendaEvent.SelectDateAction(it.toString()))
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.size(34.dp),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Colors.Light),
                contentPadding = PaddingValues(0.dp),
                onClick = { mDisplayMenu = true }
            ) {
                Text(
                    text = state.username,
                    fontWeight = FontWeight.Bold,
                    color = Colors.LightBlue2
                )
            }
        }
    }
}