package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.spascoding.taskycourse.core.constants.Colors
import com.spascoding.taskycourse.core.constants.Padding
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
    viewModel.setNavigation(navigation)
    val state by viewModel.state.collectAsStateWithLifecycle()

    val onEvent: (DetailEvent) -> Unit = { event ->
        when (event) {
            is DetailEvent.PopBackStack -> {
                navController.popBackStack()
            }

            else -> viewModel.onEvent(event)
        }
    }

    DetailScreen(
        navigation = navigation,
        state = state,
        onEvent = onEvent,
    )
}

@Composable
private fun DetailScreen(
    navigation: Navigation,
    state: DetailViewModelState,
    onEvent: (DetailEvent) -> Unit,
) {
    TaskyScaffold(
        topBar = {
            Controls(
                state = state,
                onEvent = onEvent,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = Padding.MEDIUM)
        ) {
            HeaderSection(navigation)
            TitleSection(
                state = state,
                onEditTitleClick = { onEvent.invoke(DetailEvent.EditTitleClick) },
            )
            Line()
            DescriptionSection(
                state = state,
                onEditDescriptionClick = { onEvent.invoke(DetailEvent.EditDescriptionClick) },
            )
            Line()
            if (navigation is Navigation.EventDetailNavigation) {
                PhotoSection()
                Line()
            }
            StartDateSection()
            Line()
            if (navigation is Navigation.EventDetailNavigation) {
                EndDateSection()
                Line()
            }
            ReminderSection()
            Line()
            if (navigation is Navigation.EventDetailNavigation) {
                AttendeeSection()
                Line()
            }
            DeleteButton()
        }
    }
}

@Composable
fun Line() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Padding.MEDIUM)
            .height(1.dp)
            .background(color = Colors.Light)
    )
}