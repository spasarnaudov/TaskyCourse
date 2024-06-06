package com.spascoding.taskycourse.feature_agenda_screen.presentation.agenda

import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.feature_register_screen.data.local.model.userInfo
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases
) : ViewModel() {

    fun onEvent(event: AgendaEvent) {
        when (event) {
            is AgendaEvent.LogoutAction -> {
                authenticationUseCases.logoutUser.invoke(userInfo.accessToken) {
                    event.onSuccess.invoke()
                }
            }
        }
    }

}