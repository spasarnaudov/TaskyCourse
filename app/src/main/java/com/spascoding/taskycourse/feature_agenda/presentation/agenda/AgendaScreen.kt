package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.core.constants.RoundCorner
import com.spascoding.taskycourse.ui.theme.TaskyCourseTheme

@Composable
fun AgendaScreenRoot(
    viewModel: AgendaViewModel = hiltViewModel(),
) {
    AgendaScreen(onEvent = viewModel::onEvent)
}

@Composable
private fun AgendaScreen(
    onEvent: (AgendaEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
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
                }) {
                Text(
                    text = stringResource(R.string.log_in).uppercase(),
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStart = RoundCorner.LARGE,
                        topEnd = RoundCorner.LARGE,
                    )
                ),
        ) {
        }
    }
}

@Composable
fun PreviewAgendaScreen() {
    val mockOnEvent: (AgendaEvent) -> Unit = { event ->
        // Handle the event or leave it empty for the preview
    }

    AgendaScreen(
        onEvent = mockOnEvent
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TaskyCourseTheme {
        PreviewAgendaScreen()
    }
}