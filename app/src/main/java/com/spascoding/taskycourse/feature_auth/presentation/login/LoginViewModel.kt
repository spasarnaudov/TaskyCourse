package com.spascoding.taskycourse.feature_auth.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.core.data.onError
import com.spascoding.taskycourse.core.data.onSuccess
import com.spascoding.taskycourse.core.presentation.UiText
import com.spascoding.taskycourse.core.presentation.asUiText
import com.spascoding.taskycourse.feature_auth.domain.repository.AuthRepository
import com.spascoding.taskycourse.feature_auth.domain.util.UserDataValidator
import com.spascoding.taskycourse.feature_auth.presentation.util.asUiText
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
            is LoginEvent.ChangeEmail -> { handleChangeEmailAction(event) }
            is LoginEvent.ChangePassword -> { handleChangePasswordAction(event) }
            is LoginEvent.LoginAction -> { handleLoginAction(event) }
            is LoginEvent.SignUpAction -> {}
        }
    }

    private fun handleChangeEmailAction(event: LoginEvent.ChangeEmail) {
        UserDataValidator.validateEmail(event.email)
            .onSuccess {
                state.update {
                    it.copy(
                        email = event.email,
                        validEmail = true,
                    )
                }
            }
            .onError {
                state.update {
                    it.copy(
                        email = event.email,
                        validEmail = false,
                    )
                }
            }
    }
    private fun handleChangePasswordAction(event: LoginEvent.ChangePassword) {
        UserDataValidator.validateEmail(event.password)
            .onSuccess {
                state.update {
                    it.copy(
                        password = event.password,
                        validPassword = true,
                    )
                }
            }
            .onError {
                state.update {
                    it.copy(
                        password = event.password,
                        validPassword = false,
                    )
                }
            }
    }
    private fun handleLoginAction(event: LoginEvent.LoginAction) {
        viewModelScope.launch(Dispatchers.IO) {
            UserDataValidator.validateEmail(event.email)
                .onError { error ->
                    val errorMassage = error.asUiText()
                    _toastChannel.send(UserEvent.Error(errorMassage))
                    return@launch
                }

            UserDataValidator.validatePassword(event.password)
                .onError { error ->
                    val errorMassage = error.asUiText()
                    _toastChannel.send(UserEvent.Error(errorMassage))
                    return@launch
                }

            authRepository.login(
                email = event.email,
                password = event.password,
            ).onError { error ->
                val errorMassage = error.asUiText()
                _toastChannel.send(UserEvent.Error(errorMassage))
            }
        }
    }

    sealed interface UserEvent {
        data class Error(val error: UiText) : UserEvent
    }
}