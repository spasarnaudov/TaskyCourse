package com.spascoding.taskycourse.feature_auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.core.data.onError
import com.spascoding.taskycourse.core.data.onSuccess
import com.spascoding.taskycourse.core.presentation.UiText
import com.spascoding.taskycourse.core.presentation.asUiText
import com.spascoding.taskycourse.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _toastChannel = Channel<UserEvent>(Channel.BUFFERED)
    val toastMessages: Flow<UserEvent> = _toastChannel.receiveAsFlow()

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
                    authRepository.register(
                        name = state.value.name,
                        email = state.value.email,
                        password = state.value.password,
                    ).onSuccess {
                        authRepository.login(
                            email = state.value.email,
                            password = state.value.password,
                        )
                    }.onError { error ->
                        val errorMassage = error.asUiText()
                        _toastChannel.send(UserEvent.Error(errorMassage))
                    }
                }
            }

            is RegisterEvent.BackAction -> {}
        }
    }

    sealed interface UserEvent {
        data class Error(val error: UiText): UserEvent
    }
}