package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.rounded.PanoramaFishEye
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spascoding.taskycourse.core.constants.Colors
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.DetailEvent
import com.spascoding.taskycourse.feature_agenda.presentation.detail_screen.DetailViewModelState

@Composable
fun TitleSection(
    state: DetailViewModelState,
    onEvent: (DetailEvent) -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Rounded.PanoramaFishEye,
            contentDescription = null,
            tint = Colors.Black,
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = Padding.SMALL),
            text = stringResource(id = state.title),
            color = Colors.Black,
            fontSize = 26.sp,
        )
        if (state.isEditMode) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            color = Color.Black
                        ),
                        onClick = {
                            onEvent.invoke(DetailEvent.EditTitleClick)
                        },
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Filled.ChevronRight,
                    contentDescription = null,
                    tint = Colors.Black,
                )
            }
        }
    }
}