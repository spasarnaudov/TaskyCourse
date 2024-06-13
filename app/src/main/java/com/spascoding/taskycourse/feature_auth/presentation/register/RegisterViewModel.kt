package com.spascoding.taskycourse.feature_auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.core.onError
import com.spascoding.taskycourse.core.onSuccess
import com.spascoding.taskycourse.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var state = MutableStateFlow(RegisterViewModelState())
        private set

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.ChangeName -> {
                state.update {
                    it.copy(name = event.name)
                }
            }

            is RegisterEvent.ChangeEmailAddress -> {
                state.update {
                    it.copy(email = event.email)
                }
            }

            is RegisterEvent.ChangePassword -> {
                state.update {
                    it.copy(password = event.password)
                }
            }

            is RegisterEvent.RegisterAction -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val registerResponse = authRepository.register(
                        name = state.value.name,
                        email = state.value.email,
                        password = state.value.password,
                    )
                    registerResponse.onSuccess {
                        authRepository.login(
                            email = state.value.email,
                            password = state.value.password,
                        )
                    }.onError {
                        //TODO
//                        state.update { it.copy(error = error.toUiText()) }
                    }
                }
            }

            is RegisterEvent.BackAction -> {}
        }
    }
}