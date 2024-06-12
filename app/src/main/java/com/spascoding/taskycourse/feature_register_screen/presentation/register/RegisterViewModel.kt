package com.spascoding.taskycourse.feature_register_screen.presentation.register

import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticationUseCases
import com.spascoding.taskycourse.feature_register_screen.presentation.util.AuthPattern
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
    private val authenticationUseCases: AuthenticationUseCases
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
                    val registerResponse = authenticationUseCases.registerUser.invoke(
                        name = state.value.name,
                        email = state.value.email,
                        password = state.value.password,
                    )
                    if (registerResponse.isSuccessful) {
                        val response = authenticationUseCases.loginUser.invoke(
                            email = state.value.email,
                            password = state.value.password,
                        )
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