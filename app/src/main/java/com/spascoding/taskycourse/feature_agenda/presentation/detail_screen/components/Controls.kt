package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.DetailEvent
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.DetailViewModelState
import com.spascoding.taskycourse.feature_agenda.presentation.utils.DATE_FORMAT

@Composable
fun Controls(
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