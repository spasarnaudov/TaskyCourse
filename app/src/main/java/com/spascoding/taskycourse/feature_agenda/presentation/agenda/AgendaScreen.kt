package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.spascoding.taskycourse.core.constants.RoundCorner
import com.spascoding.taskycourse.core.presentation.ObserveAsEvents
import com.spascoding.taskycourse.ui.theme.TaskyCourseTheme
import java.time.LocalDate

@Composable
fun AgendaScreenRoot(
    viewModel: AgendaViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.toastMessages, onEvent = { event ->
        val errorMessage = when (event) {
            is AgendaViewModel.UserEvent.Error -> event.error.asString(context)
        }
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    })

    val state by viewModel.state.collectAsStateWithLifecycle()

    AgendaScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
private fun AgendaScreen(
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AgendaControls(
            state,
            onEvent,
        )
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
            AgendaDaysList(
                state,
                onEvent,
            )
        }
    }
}

@Composable
fun PreviewAgendaScreen() {
    val state = AgendaViewModelState(
        username = "SA",
        calendarDate = LocalDate.now(),
    )
    val mockOnEvent: (AgendaEvent) -> Unit = { event ->
        // Handle the event or leave it empty for the preview
    }

    AgendaScreen(
        state = state,
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