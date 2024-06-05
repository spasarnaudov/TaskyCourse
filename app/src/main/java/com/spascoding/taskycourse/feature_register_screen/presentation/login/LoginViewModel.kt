package com.spascoding.taskycourse.feature_register_screen.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {

    private val _state = mutableStateOf(LoginViewModelState())
    val state: State<LoginViewModelState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ChangeEmail -> {
                _state.value = state.value.copy(
                    email = event.email
                )
            }
            is LoginEvent.ChangePassword -> {
                _state.value = state.value.copy(
                    password = event.password
                )
            }
            is LoginEvent.LoginAction -> {
                authenticationUseCases.loginUser.invoke(
                    email = _state.value.email,
                    password = _state.value.password,
                )
            }
            is LoginEvent.SignUpAction -> {}
        }
    }
}