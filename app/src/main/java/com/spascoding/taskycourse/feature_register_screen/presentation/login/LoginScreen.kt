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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.constants.FontSize
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.core.constants.RoundCorner
import com.spascoding.taskycourse.feature_register_screen.presentation.register.CustomOutlinedTextField
import com.spascoding.taskycourse.feature_register_screen.presentation.register.PasswordOutlinedTextField
import com.spascoding.taskycourse.navigation.Navigation

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(vertical = Padding.LARGE),
            text = "Welcome Back!",
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
            CustomOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Padding.MEDIUM,
                        top = Padding.MEDIUM,
                        end = Padding.MEDIUM,
                    ),
                value = viewModel.state.value.emailAddress,
                placeholder = stringResource(R.string.email_address),
                valid = true,   //TODO add check for email existing
                onValueChange = {
                    viewModel.onEvent(LoginEvent.ChangeEmailAddress(it))
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
                value = viewModel.state.value.password,
                placeholder = stringResource(R.string.password),
                onValueChange = {
                    viewModel.onEvent(LoginEvent.ChangePassword(it))
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
                    viewModel.onEvent(LoginEvent.LoginAction)
                }) {
                Text(
                    text = "Log in".uppercase(),
                    fontWeight = FontWeight.Bold,
                )
            }
            val doNotHaveAccount = buildAnnotatedString {
                append("DON’T HAVE AN ACCOUNT? ")
                pushStringAnnotation(tag = "click", annotation = "click")
                withStyle(
                    SpanStyle(
                        textDecoration = TextDecoration.Underline,
                        color = Color.Blue
                    )
                ) {
                    append("SIGN UP")
                }
                pop()
            }
            val context = LocalContext.current
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
                    doNotHaveAccount.getStringAnnotations(tag = "click", start = offset, end = offset).firstOrNull()
                        ?.let {
                            navController.navigate(Navigation.RegisterNavigation.route)
                            viewModel.onEvent(LoginEvent.SignUpAction)
                        }
                }
            }
        }
    }
}