package com.spascoding.taskycourse.feature_agenda_screen.presentation.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfoManager
import com.spascoding.taskycourse.feature_register_screen.domain.use_case.AuthenticationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases,
    private val userInfoManager: UserInfoManager,
) : ViewModel() {

    fun onEvent(event: AgendaEvent) {
        when (event) {
            is AgendaEvent.LogoutAction -> {
                viewModelScope.launch {
                    userInfoManager.userInfoFlow.collect { userInfo ->
                        if (userInfo != null) {
                            authenticationUseCases.logoutUser.invoke(userInfo.accessToken) {
                                event.onSuccess.invoke()
                                viewModelScope.launch {
                                    userInfoManager.clearUserInfo()
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}