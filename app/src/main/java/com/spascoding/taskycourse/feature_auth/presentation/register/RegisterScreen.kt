package com.spascoding.taskycourse.feature_auth.presentation.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.constants.FontSize
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.core.constants.RoundCorner
import com.spascoding.taskycourse.feature_auth.presentation.components.DefaultTextField
import com.spascoding.taskycourse.feature_auth.presentation.components.PasswordOutlinedTextField
import com.spascoding.taskycourse.feature_auth.presentation.util.AuthPattern
import com.spascoding.taskycourse.navigation.Navigation
import com.spascoding.taskycourse.ui.theme.TaskyCourseTheme

@Composable
fun RegisterScreenRoot(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val onNavigationEvent: (RegisterEvent) -> Unit = { event ->
        when (event) {
            is RegisterEvent.BackAction -> {
                navController.navigateUp()
            }
            is RegisterEvent.RegisterAction -> {
                navController.navigate(Navigation.AgendaNavigation.route)
            }
            else -> viewModel.onEvent(event)
        }
    }
    RegisterScreen(
        state = state,
        onEvent = onNavigationEvent
    )
}

@Composable
private fun RegisterScreen(
    state: RegisterViewModelState,
    onEvent: (RegisterEvent) -> Unit
) {
    val validName: Boolean = AuthPattern.isValidName(state.name)
    val validEmail: Boolean = AuthPattern.isValidEmail(state.email)
    val validPassword: Boolean = AuthPattern.isValidPassword(state.password)
    val canRegister: Boolean = validName && validEmail && validPassword

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(vertical = Padding.LARGE),
            text = stringResource(R.string.create_your_account),
            fontSize = FontSize.LARGE,
            fontWeight = FontWeight.Bold,
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
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Padding.MEDIUM,
                        top = Padding.LARGE,
                        end = Padding.MEDIUM,
                    ),
                value = state.name,
                placeholder = stringResource(R.string.name),
                valid = validName,
                onValueChange = {
                    onEvent(RegisterEvent.ChangeName(it))
                },
            )
            DefaultTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Padding.MEDIUM,
                        top = Padding.MEDIUM,
                        end = Padding.MEDIUM,
                    ),
                value = state.email,
                placeholder = stringResource(R.string.email_address),
                valid = validEmail,
                onValueChange = {
                    onEvent(RegisterEvent.ChangeEmailAddress(it))
                },
            )
            PasswordOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Padding.MEDIUM,
                        top = Padding.MEDIUM,
                        end = Padding.MEDIUM,
                    ),
                value = state.password,
                placeholder = stringResource(R.string.password),
                onValueChange = {
                    onEvent(RegisterEvent.ChangePassword(it))
                },
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(
                        start = Padding.MEDIUM,
                        top = Padding.LARGE,
                        end = Padding.MEDIUM,
                    ),
                enabled = canRegister,
                onClick = {
                    onEvent(RegisterEvent.RegisterAction)
                }) {
                Text(
                    text = stringResource(R.string.get_started).uppercase(),
                    fontWeight = FontWeight.Bold,
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = Padding.LARGE),
                contentAlignment = Alignment.BottomStart
            ) {
                Button(
                    modifier = Modifier
                        .size(100.dp)
                        .padding(Padding.MEDIUM),
                    shape = RoundedCornerShape(RoundCorner.MEDIUM),
                    onClick = {
                        onEvent(RegisterEvent.BackAction)
                    }) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowLeft,
                        contentDescription = null
                    )
                }
            }

        }
    }
}

@Composable
fun PreviewRegisterScreen() {
    val sampleState = RegisterViewModelState(
        name = "test name",
        email = "test email",
        password = "test password",
    )

    val mockOnEvent: (RegisterEvent) -> Unit = { event ->
        // Handle the event or leave it empty for the preview
    }

    RegisterScreen(
        state = sampleState,
        onEvent = mockOnEvent
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TaskyCourseTheme {
        PreviewRegisterScreen()
    }
}