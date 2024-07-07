package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.constants.RoundCorner
import com.spascoding.taskycourse.core.presentation.ObserveAsEvents
import com.spascoding.taskycourse.core.presentation.fab.ExpandableFab
import com.spascoding.taskycourse.core.presentation.fab.FabItem
import com.spascoding.taskycourse.feature_agenda.constants.AgendaItemType
import com.spascoding.taskycourse.feature_agenda.domain.agenda.model.AgendaItem
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.components.AgendaControls
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.components.AgendaDaysList
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.components.AgendaItemView
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun AgendaScreen(
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit
) {
    Scaffold(
        floatingActionButton = {
            ExpandableFab(
                fabItems = listOf(
                    FabItem(id = AgendaItemType.EVENT.ordinal, text = stringResource(R.string.event)),
                    FabItem(id = AgendaItemType.TASK.ordinal, text = stringResource(R.string.task)),
                    FabItem(id = AgendaItemType.REMAINDER.ordinal, text = stringResource(R.string.remainder)),
                ),
                onFABClick = {
                    when (it) {
                        AgendaItemType.EVENT.ordinal -> {}
                        AgendaItemType.TASK.ordinal -> {}
                        AgendaItemType.REMAINDER.ordinal -> {}
                    }
                }
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    state = rememberLazyListState(),
                ) {
                    items(state.agendaItems.size) { index ->
                        AgendaItemView(
                            agendaItem = state.agendaItems[index],
                            state = state,
                            onEvent = onEvent
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PreviewAgendaScreen() {
    val state = AgendaViewModelState(
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