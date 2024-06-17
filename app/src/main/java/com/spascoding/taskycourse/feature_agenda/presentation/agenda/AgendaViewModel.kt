package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.core.data.onError
import com.spascoding.taskycourse.core.presentation.UiText
import com.spascoding.taskycourse.core.presentation.asUiText
import com.spascoding.taskycourse.feature_agenda.domain.repository.AgendaRepository
import com.spascoding.taskycourse.feature_auth.presentation.login.LoginViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val agendaRepository: AgendaRepository,
) : ViewModel() {

    private val _toastChannel = Channel<UserEvent>(Channel.BUFFERED)
    val toastMessages: Flow<UserEvent> = _toastChannel.receiveAsFlow()

    fun onEvent(event: AgendaEvent) {
        when (event) {
            is AgendaEvent.LogoutAction -> {
                viewModelScope.launch {
                    agendaRepository.logout()
                        .onError { error ->
                            val errorMassage = error.asUiText()
                            _toastChannel.send(UserEvent.Error(errorMassage))
                        }
                }
            }
        }
    }

    sealed interface UserEvent {
        data class Error(val error: UiText) : UserEvent
    }

}