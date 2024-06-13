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
    //TODO remove AuthRepository
    private val authRepository: AuthRepository,
) : ViewModel() {

    fun onEvent(event: AgendaEvent) {
        when (event) {
            is AgendaEvent.LogoutAction -> {
                viewModelScope.launch {
                    authRepository.logout()
                }
            }
        }
    }

}