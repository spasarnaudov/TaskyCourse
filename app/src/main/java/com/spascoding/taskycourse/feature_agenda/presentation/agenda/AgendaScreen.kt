package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.presentation.ObserveAsEvents
import com.spascoding.taskycourse.core.presentation.fab.ExpandableFab
import com.spascoding.taskycourse.core.presentation.fab.FabItem
import com.spascoding.taskycourse.feature_agenda.constants.AgendaItemType
import com.spascoding.taskycourse.feature_agenda.domain.agenda.model.AgendaItem
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.components.AgendaControls
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.components.AgendaDaysList
import com.spascoding.taskycourse.feature_agenda.presentation.agenda.components.AgendaItemView
import com.spascoding.taskycourse.core.presentation.components.TaskyScaffold
import com.spascoding.taskycourse.navigation.Navigation
import com.spascoding.taskycourse.ui.theme.TaskyCourseTheme
import java.time.LocalDate

@Composable
fun AgendaScreenRoot(
    navController: NavController,
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

    val onNavigationEvent: (AgendaEvent) -> Unit = { event ->
        when (event) {
            is AgendaEvent.SelectAgendaItemMenu -> navController.navigate(event.route)
            else -> viewModel.onEvent(event)
        }
    }

    AgendaScreen(
        state = state,
        onEvent = onNavigationEvent
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
                        AgendaItemType.EVENT.ordinal -> { onEvent.invoke(AgendaEvent.SelectAgendaItemMenu(Navigation.EventDetailNavigation.route + "?navigation=${Navigation.EventDetailNavigation.route}")) }
                        AgendaItemType.TASK.ordinal -> { onEvent.invoke(AgendaEvent.SelectAgendaItemMenu(Navigation.TaskDetailNavigation.route + "?navigation=${Navigation.TaskDetailNavigation.route}")) }
                        AgendaItemType.REMAINDER.ordinal -> { onEvent.invoke(AgendaEvent.SelectAgendaItemMenu(Navigation.RemainderDetailNavigation.route  + "?navigation=${Navigation.RemainderDetailNavigation.route}")) }
                    }
                }
            )
        },
    ) { innerPadding ->
        TaskyScaffold(
            topBar = {
                AgendaControls(
                    state,
                    onEvent,
                )
            }
        ) {
            AgendaContent(
                innerPadding = innerPadding,
                state = state,
                onEvent = onEvent
            )
        }
    }
}

@Composable
private fun AgendaContent(
    innerPadding: PaddingValues,
    state: AgendaViewModelState,
    onEvent: (AgendaEvent) -> Unit
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