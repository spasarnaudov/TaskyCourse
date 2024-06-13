package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.feature_auth.data.local.model.UserInfoManager
import com.spascoding.taskycourse.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userInfoManager: UserInfoManager,
) : ViewModel() {

    fun onEvent(event: AgendaEvent) {
        when (event) {
            is AgendaEvent.LogoutAction -> {
                viewModelScope.launch {
                    val userInfo = userInfoManager.userInfoFlow.first()
                    if (userInfo != null) {
                        authRepository.logout(userInfo.accessToken)
                    }
                }
            }
        }
    }

}