package com.spascoding.taskycourse.feature_register_screen.presentation.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticationUseCases
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.TAG
import com.spascoding.taskycourse.feature_register_screen.presentation.util.AuthPattern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases,
) : ViewModel() {

    var state by mutableStateOf(LoginViewModelState())
        private set

    @OptIn(DelicateCoroutinesApi::class)
    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.ChangeEmail -> {
                state = state.copy(
                    email = event.email
                )
            }
            is LoginEvent.ChangePassword -> {
                state = state.copy(
                    password = event.password
                )
            }
            is LoginEvent.LoginAction -> {
                GlobalScope.launch(Dispatchers.IO) {
                    val response = authenticationUseCases.loginUser.invoke(
                        email = state.email,
                        password = state.password,
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

    fun validEmail(): Boolean = AuthPattern.EMAIL(state.email)
    fun validPassword(): Boolean = AuthPattern.PASSWORD(state.password)
    fun canLogin(): Boolean = validEmail() && validPassword()

}