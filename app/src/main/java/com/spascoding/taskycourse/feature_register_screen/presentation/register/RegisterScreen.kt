package com.spascoding.taskycourse.feature_register_screen.presentation.register

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.constants.FontSize
import com.spascoding.taskycourse.core.constants.Padding
import com.spascoding.taskycourse.core.constants.RoundCorner

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = hiltViewModel()
) {
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
            CustomOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Padding.MEDIUM,
                        top = Padding.LARGE,
                        end = Padding.MEDIUM,
                    ),
                value = viewModel.state.value.name,
                placeholder = stringResource(R.string.name),
                valid = false,   //TODO add check for email existing
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.ChangeName(it))
                },
            )
            CustomOutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = Padding.MEDIUM,
                        top = Padding.MEDIUM,
                        end = Padding.MEDIUM,
                    ),
                value = viewModel.state.value.email,
                placeholder = stringResource(R.string.email_address),
                valid = true,   //TODO add check for email existing
                onValueChange = {
                    viewModel.onEvent(RegisterEvent.ChangeEmailAddress(it))
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
                    viewModel.onEvent(RegisterEvent.ChangePassword(it))
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
                    viewModel.onEvent(RegisterEvent.RegisterAction)
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
                        navController.popBackStack()
                        viewModel.onEvent(RegisterEvent.BackAction)
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