package com.spascoding.taskycourse.feature_auth.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.R
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
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    private val _toastChannel = Channel<UserEvent>(Channel.BUFFERED)
    val toastMessages: Flow<UserEvent> = _toastChannel.receiveAsFlow()

    var state = MutableStateFlow(RegisterViewModelState())
        private set

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.ChangeName -> { handleChangeNameAction(event) }
            is RegisterEvent.ChangeEmail -> { handleChangeEmailAction(event) }
            is RegisterEvent.ChangePassword -> { handleChangePasswordAction(event) }
            is RegisterEvent.RegisterAction -> { handleRegisterAction(event) }
            is RegisterEvent.BackAction -> {}
        }
    }

    private fun handleChangeNameAction(event: RegisterEvent.ChangeName) {
        UserDataValidator.validateName(event.name)
            .onSuccess {
                state.update {
                    it.copy(
                        name = event.name,
                        validName = true,
                    )
                }
            }
            .onError {
                state.update {
                    it.copy(
                        name = event.name,
                        validName = false,
                    )
                }
            }
    }
    private fun handleChangeEmailAction(event: RegisterEvent.ChangeEmail) {
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
    private fun handleChangePasswordAction(event: RegisterEvent.ChangePassword) {
        UserDataValidator.validatePassword(event.password)
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
    private fun handleRegisterAction(event: RegisterEvent.RegisterAction) {
        viewModelScope.launch(Dispatchers.IO) {
            val messages = mutableListOf<UiText>()
            UserDataValidator.validateName(event.name)
                .onError { error ->
                    val errorMassage = error.asUiText()
                    messages.add(errorMassage)
                }
            UserDataValidator.validateEmail(event.email)
                .onError { error ->
                    val errorMassage = error.asUiText()
                    messages.add(errorMassage)
                }
            UserDataValidator.validatePassword(event.password)
                .onError { error ->
                    val errorMassage = error.asUiText()
                    messages.add(errorMassage)
                }

            if (messages.isNotEmpty()) {
                _toastChannel.send(UserEvent.Error(messages))
                return@launch
            }

            authRepository.register(
                name = event.name,
                email = event.email,
                password = event.password,
            ).onSuccess {
                authRepository.login(
                    email = event.email,
                    password = event.password,
                )
                val message = UiText.StringResource(R.string.logged_in)
                messages.add(message)
                _toastChannel.send(UserEvent.Success(messages))
            }.onError { error ->
                val message = error.asUiText()
                messages.add(message)
                _toastChannel.send(UserEvent.Error(messages))
            }
        }
    }

    sealed interface UserEvent {
        data class Success(val messages: List<UiText>) : UserEvent
        data class Error(val messages: List<UiText>) : UserEvent
    }
}