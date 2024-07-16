package com.spascoding.taskycourse.feature_agenda.presentation.event

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.spascoding.taskycourse.core.presentation.ObserveAsEvents
import com.spascoding.taskycourse.feature_agenda.domain.agenda.model.AgendaItem
import com.spascoding.taskycourse.core.presentation.components.TaskyScaffold
import com.spascoding.taskycourse.ui.theme.TaskyCourseTheme
import java.time.LocalDate

@Composable
fun EventScreenRoot(
    viewModel: EventViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.toastMessages, onEvent = { event ->
        val errorMessage = when (event) {
            is EventViewModel.UserEvent.Error -> event.error.asString(context)
        }
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    })

    val state by viewModel.state.collectAsStateWithLifecycle()

    EventScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun EventScreen(
    state: EventViewModelState,
    onEvent: (EventEvent) -> Unit
) {
    TaskyScaffold(
        topBar = {
            Text(text = "topBar")
        }
    ) {
        Text(text = "content")
    }
}

@Composable
fun PreviewEventScreen() {
    val state = EventViewModelState(
        username = "SA",
        calendarDate = LocalDate.now(),
        agendaItems = listOf(
            AgendaItem(
                title = "Title",
                description = "Description",
                from = LocalDate.now(),
                to = LocalDate.now(),
                done = true,
            ),
            AgendaItem(
                title = "Title",
                description = "Description",
                from = LocalDate.now(),
                done = false,
            ),
        )
    )
    val mockOnEvent: (EventEvent) -> Unit = { event ->
        // Handle the event or leave it empty for the preview
    }

    EventScreen(
        state = state,
        onEvent = mockOnEvent
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TaskyCourseTheme {
        PreviewEventScreen()
    }
}