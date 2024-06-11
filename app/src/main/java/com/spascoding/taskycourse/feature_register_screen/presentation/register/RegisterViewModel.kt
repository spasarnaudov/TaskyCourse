package com.spascoding.taskycourse.feature_register_screen.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticationUseCases
import com.spascoding.taskycourse.feature_register_screen.presentation.util.AuthPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {

    private val _state = mutableStateOf(RegisterViewModelState())
    val state: State<RegisterViewModelState> = _state

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.ChangeName -> {
                _state.value = state.value.copy(
                    name = event.name
                )
            }
            is RegisterEvent.ChangeEmailAddress -> {
                _state.value = state.value.copy(
                    email = event.email
                )
            }
            is RegisterEvent.ChangePassword -> {
                _state.value = state.value.copy(
                    password = event.password
                )
            }
            is RegisterEvent.RegisterAction -> {
                authenticationUseCases.registerUser.invoke(
                    name = _state.value.name,
                    email = _state.value.email,
                    password = _state.value.password,
                ) { email, password ->
                    authenticationUseCases.loginUser.invoke(email, password) {
                        event.onSuccess.invoke(it.userId)
                    }
                }
            }
            is RegisterEvent.BackAction -> {}
        }
    }

    fun validName(): Boolean = AuthPattern.NAME(state.value.name)
    fun validEmail(): Boolean = AuthPattern.EMAIL(state.value.email)
    fun validPassword(): Boolean = AuthPattern.PASSWORD(state.value.password)
    fun canRegister(): Boolean = validName() && validEmail() && validPassword()
}