package com.spascoding.taskycourse.core.presentation.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ExpandableFab(
    containerColor: Color = MaterialTheme.colorScheme.primary,
    fabItems: List<FabItem>,
    onFABClick: (Int) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.End) {

        // Additional FABs
        AnimatedVisibility(visible = expanded) {
            Column {
                fabItems.forEach { fabItem ->
                    FloatingActionButton(
                        containerColor = containerColor,
                        onClick = {
                            expanded = false
                            onFABClick.invoke(fabItem.id)
                        },
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.End)
                    ) {
                        if (fabItem.text.isNotEmpty()) {
                            Text(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                text = fabItem.text
                            )
                        }
                        if (fabItem.icon != null) {
                            Icon(
                                fabItem.icon,
                                contentDescription = fabItem.text
                            )
                        }
                    }
                }
            }
        }

        // Main FAB
        FloatingActionButton(
            containerColor = containerColor,
            onClick = { expanded = !expanded }
        ) {
            Icon(
                if (expanded) Icons.Filled.Close else Icons.Filled.Add,
                contentDescription = ""
            )
        }
    }
}