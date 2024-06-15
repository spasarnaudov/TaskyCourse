package com.spascoding.taskycourse.feature_auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.core.data.onError
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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _toastChannel = Channel<UserEvent>(Channel.BUFFERED)
    val toastMessages: Flow<UserEvent> = _toastChannel.receiveAsFlow()

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
                    authRepository.login(
                        email = state.value.email,
                        password = state.value.password,
                    ).onError { error ->
                        val errorMassage = error.asUiText()
                        _toastChannel.send(UserEvent.Error(errorMassage))
                    }
                }
            }
            is LoginEvent.SignUpAction -> {}
        }
    }

    sealed interface UserEvent {
        data class Error(val error: UiText): UserEvent
    }
}