package com.spascoding.taskycourse.feature_register_screen.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _state = mutableStateOf(LoginViewModelState())
    val state: State<LoginViewModelState> = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ChangeEmailAddress -> {
                _state.value = state.value.copy(
                    emailAddress = event.emailAddress
                )
            }
            is LoginEvent.ChangePassword -> {
                _state.value = state.value.copy(
                    password = event.password
                )
            }
            is LoginEvent.LoginAction -> TODO()
            is LoginEvent.SignUpAction -> {}
        }
    }
}