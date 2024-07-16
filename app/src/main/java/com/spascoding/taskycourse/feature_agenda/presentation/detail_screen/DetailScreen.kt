package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.spascoding.taskycourse.core.presentation.components.TaskyScaffold
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.AttendeeSection
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.Controls
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.DeleteButton
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.DescriptionSection
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.EndDateSection
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.HeaderSection
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.PhotoSection
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.ReminderSection
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.StartDateSection
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components.TitleSection
import com.spascoding.taskycourse.navigation.Navigation

@Composable
fun DetailScreenRoot(
    navController: NavController,
    navigation: Navigation,
    viewModel: DetailViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val onEvent: (DetailEvent) -> Unit = { event ->
        when (event) {
            else -> viewModel.onEvent(event)
        }
    }

    DetailScreen(
        navController = navController,
        navigation = navigation,
        state = state,
        onEvent = onEvent,
    )
}

@Composable
private fun DetailScreen(
    navController: NavController,
    navigation: Navigation,
    state: DetailViewModelState,
    onEvent: (DetailEvent) -> Unit,
) {
    TaskyScaffold(
        topBar = {
            Controls(
                navController = navController,
                state = state,
                onEvent = onEvent,
            )
        }
    ) {
        HeaderSection(navigation)
        TitleSection()
        DescriptionSection()
        if (navigation is Navigation.EventDetailNavigation) {
            PhotoSection()
        }
        StartDateSection()
        if (navigation is Navigation.EventDetailNavigation) {
            EndDateSection()
        }
        ReminderSection()
        if (navigation is Navigation.EventDetailNavigation) {
            AttendeeSection()
        }
        DeleteButton()
    }
}