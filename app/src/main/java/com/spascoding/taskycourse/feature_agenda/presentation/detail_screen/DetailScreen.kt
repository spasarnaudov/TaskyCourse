package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.presentation.components.TaskyScaffold
import com.spascoding.taskycourse.feature_agenda.presentation.utils.DATE_FORMAT
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
            HeaderSection(
                navController = navController,
                state = state,
                onEvent = onEvent,
            )
        }
    ) {
        TitleSection()
        DescriptionSection()
        if(navigation is Navigation.EventDetailNavigation) {
            PhotoSection()
        }
        StartDateSection()
        if(navigation is Navigation.EventDetailNavigation) {
            EndDateSection()
        }
        ReminderSection()
        if(navigation is Navigation.EventDetailNavigation) {
            AttendeeSection()
        }
        DeleteButton()
    }
}

@Composable
fun HeaderSection(
    navController: NavController,
    state: DetailViewModelState,
    onEvent: (DetailEvent) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(onClick = { navController.popBackStack() }) {
            Icon(imageVector = Icons.Outlined.Close, contentDescription = null)
        }
        Text(
            modifier = Modifier.weight(1f),
            text = state.selectedDate.format(DATE_FORMAT).uppercase(),
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
        )
        if (state.isEditMode) {
            Button(onClick = { onEvent.invoke(DetailEvent.SaveAction) }) {
                Text(text = stringResource(R.string.save))
            }
        } else {
            Button(onClick = { onEvent.invoke(DetailEvent.EditAction) }) {
                Icon(imageVector = Icons.Outlined.Edit, contentDescription = null)
            }
        }
    }
}

@Composable
fun TitleSection() {

}

@Composable
fun DescriptionSection() {

}

@Composable
fun PhotoSection() {

}

@Composable
fun StartDateSection() {

}

@Composable
fun EndDateSection() {

}

@Composable
fun ReminderSection() {

}

@Composable
fun AttendeeSection() {

}

@Composable
fun DeleteButton() {

}