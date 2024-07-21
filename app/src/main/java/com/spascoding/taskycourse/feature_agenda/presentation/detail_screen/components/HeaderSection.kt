package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.constants.Colors
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.navigation.Navigation

@Composable
fun HeaderSection(
    navigation: Navigation,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Padding.MEDIUM, vertical = Padding.MED_LARGE),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (navigation is Navigation.TaskDetailNavigation) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Colors.Green)
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(id = R.string.task),
                color = Color.Black
            )
        } else if (navigation is Navigation.EventDetailNavigation) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Colors.LightGreen)
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(id = R.string.event),
                color = Color.Black
            )
        } else if (navigation is Navigation.RemainderDetailNavigation) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .background(Colors.LightGray)
            )
            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = stringResource(id = R.string.remainder),
                color = Color.Black
            )
        }
    }
}