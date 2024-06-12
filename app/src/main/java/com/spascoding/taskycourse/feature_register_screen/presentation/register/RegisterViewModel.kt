package com.spascoding.taskycourse.feature_register_screen.presentation.register

import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.feature_register_screen.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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

    @OptIn(DelicateCoroutinesApi::class)
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
                GlobalScope.launch(Dispatchers.IO) {
                    val registerResponse = authRepository.register(
                        name = state.value.name,
                        email = state.value.email,
                        password = state.value.password,
                    )
                    if (registerResponse.isSuccessful) {
                        val response = authRepository.login(
                            email = state.value.email,
                            password = state.value.password,
                        )
                    }
                }
            }

            is RegisterEvent.BackAction -> {}
        }
    }
}