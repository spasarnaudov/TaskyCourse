package com.spascoding.taskycourse.feature_auth.presentation.login

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
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
import com.spascoding.taskycourse.core.presentation.ObserveAsEvents
import com.spascoding.taskycourse.feature_auth.presentation.components.DefaultTextField
import com.spascoding.taskycourse.feature_auth.presentation.components.PasswordOutlinedTextField
import com.spascoding.taskycourse.navigation.Navigation
import com.spascoding.taskycourse.ui.theme.TaskyCourseTheme

@Composable
fun LoginScreenRoot(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    ObserveAsEvents(flow = viewModel.toastMessages, onEvent = { event ->
        val errorMessage = when (event) {
            is LoginViewModel.UserEvent.Error -> event.error.asString(context)
        }
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    })

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
                valid = state.validEmail,
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
                onClick = {
                    onEvent(
                        LoginEvent.LoginAction(
                            state.email,
                            state.password,
                        )
                    )
                }) {
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