package com.spascoding.taskycourse.feature_register_screen.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.constants.FontSize
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.core.constants.RoundCorner
import com.spascoding.taskycourse.feature_register_screen.presentation.components.DefaultTextField
import com.spascoding.taskycourse.feature_register_screen.presentation.components.PasswordOutlinedTextField
import com.spascoding.taskycourse.feature_register_screen.presentation.util.AuthPattern
import com.spascoding.taskycourse.navigation.Navigation
import com.spascoding.taskycourse.ui.theme.TaskyCourseTheme

@Composable
fun LoginScreenRoot(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val onNavigationEvent: (LoginEvent) -> Unit = { event ->
        when (event) {
            is LoginEvent.SignUpAction -> navController.navigate(Navigation.RegisterNavigation.route)
            else -> viewModel.onEvent(event)
        }
    }

    LoginScreen(
        state = state,
        onEvent = onNavigationEvent
    )
}

@Composable
private fun LoginScreen(
    state: LoginViewModelState,
    onEvent: (LoginEvent) -> Unit
) {
    val validEmail: Boolean = AuthPattern.isValidEmail(state.email)
    val validPassword: Boolean = AuthPattern.isValidPassword(state.password)
    val canLogin: Boolean = validEmail && validPassword

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(vertical = Padding.LARGE),
            text = stringResource(R.string.welcome_back),
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
                        top = Padding.MEDIUM,
                        end = Padding.MEDIUM,
                    ),
                value = state.email,
                placeholder = stringResource(R.string.email_address),
                valid = validEmail,
                onValueChange = {
                    onEvent(LoginEvent.ChangeEmail(it))
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
                    onEvent(LoginEvent.ChangePassword(it))
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
                enabled = canLogin,
                onClick = { onEvent(LoginEvent.LoginAction) }) {
                Text(
                    text = stringResource(R.string.log_in).uppercase(),
                    fontWeight = FontWeight.Bold,
                )
            }
            val doNotHaveAccount = buildAnnotatedString {
                append(stringResource(R.string.don_t_have_an_account))
                pushStringAnnotation(tag = "click", annotation = "click")
                withStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        color = Color.Blue
                    )
                ) {
                    append(stringResource(R.string.sign_up))
                }
                pop()
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(bottom = Padding.LARGE),
                contentAlignment = Alignment.BottomCenter
            ) {
                ClickableText(
                    text = doNotHaveAccount,
                ) { offset ->
                    doNotHaveAccount.getStringAnnotations(
                        tag = "click",
                        start = offset,
                        end = offset
                    ).firstOrNull()
                        ?.let {
                            onEvent(LoginEvent.SignUpAction)
                        }
                }
            }
        }
    }
}

@Composable
fun PreviewLoginScreen() {
    val sampleState = LoginViewModelState(
        email = "test email",
        password = "test password",
    )

    val mockOnEvent: (LoginEvent) -> Unit = { event ->
        // Handle the event or leave it empty for the preview
    }

    LoginScreen(
        state = sampleState,
        onEvent = mockOnEvent
    )
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    TaskyCourseTheme {
        PreviewLoginScreen()
    }
}