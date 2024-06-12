package com.spascoding.taskycourse.feature_register_screen.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.feature_register_screen.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var state = MutableStateFlow(LoginViewModelState())
        private set

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ChangeEmail -> {
                state.update {
                    it.copy(email = event.email)
                }
            }
            is LoginEvent.ChangePassword -> {
                state.update {
                    it.copy(password = event.password)
                }
            }
            is LoginEvent.LoginAction -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val response = authRepository.login(
                        email = state.value.email,
                        password = state.value.password,
                    )
                    if (!response.isSuccessful) {
                        //TODO show error in dialog
                    }
                }
            }
            is LoginEvent.SignUpAction -> {}
        }
    }
}