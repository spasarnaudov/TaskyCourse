package com.spascoding.taskycourse.feature_register_screen.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.spascoding.taskycourse.core.constants.Colors
import com.spascoding.taskycourse.core.constants.FontSize
import com.spascoding.taskycourse.core.constants.RoundCorner

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTextField(
    value: String,
    placeholder: String,
    valid: Boolean,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(RoundCorner.SMALL),
) {
    var hasFocus by remember { mutableStateOf(false) }

    val color = if (hasFocus) {
        Colors.LightBlue
    } else if (value.isNotBlank() && valid.not()) {
        Colors.Red
    } else {
        Color.Transparent
    }

    OutlinedTextField(
        modifier = modifier
            .border(
                width = 1.dp,
                color = color,
                shape = shape,
            )
            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus },
        value = value,
        onValueChange = onValueChange,
        shape = shape,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Transparent, // Hide border when not focused
            focusedBorderColor = Color.Transparent, // Show border when focused
            containerColor = Colors.LightGray
        ),
        textStyle = TextStyle(
            color = Colors.DarkGray,
            fontSize = FontSize.MEDIUM,
        ),
        placeholder = {
            Text(text = placeholder)
        },
        trailingIcon = {
            if (valid) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = null,
                    tint = Colors.Green
                )
            }
        }
    )
}