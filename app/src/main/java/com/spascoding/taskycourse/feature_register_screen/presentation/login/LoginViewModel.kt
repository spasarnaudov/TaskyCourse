package com.spascoding.taskycourse.feature_register_screen.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticationUseCases
import com.spascoding.taskycourse.feature_register_screen.presentation.util.AuthPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases,
) : ViewModel() {

    var state by mutableStateOf(LoginViewModelState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ChangeEmail -> {
                state = state.copy(
                    email = event.email
                )
            }
            is LoginEvent.ChangePassword -> {
                state = state.copy(
                    password = event.password
                )
            }
            is LoginEvent.LoginAction -> {
                authenticationUseCases.loginUser.invoke(
                    email = state.email,
                    password = state.password,
                ) {
                    event.onSuccess.invoke(it.userId)
                }
            }
            is LoginEvent.SignUpAction -> {}
        }
    }

    fun validEmail(): Boolean = AuthPattern.EMAIL(state.email)
    fun validPassword(): Boolean = AuthPattern.PASSWORD(state.password)
    fun canLogin(): Boolean = validEmail() && validPassword()

}