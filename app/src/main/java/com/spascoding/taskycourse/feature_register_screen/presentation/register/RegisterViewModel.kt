package com.spascoding.taskycourse.feature_register_screen.presentation.register

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {

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
                    emailAddress = event.emailAddress
                )
            }
            is RegisterEvent.ChangePassword -> {
                _state.value = state.value.copy(
                    password = event.password
                )
            }
            is RegisterEvent.RegisterAction -> TODO()
            is RegisterEvent.BackAction -> TODO()
        }
    }
}