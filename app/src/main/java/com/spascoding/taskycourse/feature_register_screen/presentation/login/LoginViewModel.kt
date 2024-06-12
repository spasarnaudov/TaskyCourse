package com.spascoding.taskycourse.feature_register_screen.presentation.login

import android.util.Log
import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.feature_register_screen.domain.repository.AuthRepository
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.TAG
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
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
) : ViewModel() {

    var state = MutableStateFlow(LoginViewModelState())
        private set

    @OptIn(DelicateCoroutinesApi::class)
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
                GlobalScope.launch(Dispatchers.IO) {
                    val response = authRepository.login(
                        email = state.value.email,
                        password = state.value.password,
                    )
                    if (response.isSuccessful.not()) {
                        //TODO show error in dialog
                        Log.e(TAG, response.code().toString())
                    }
                }
            }
            is LoginEvent.SignUpAction -> {}
        }
    }
}