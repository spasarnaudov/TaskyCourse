package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.data.local.UserInfoManager
import com.spascoding.taskycourse.core.data.onError
import com.spascoding.taskycourse.core.data.onSuccess
import com.spascoding.taskycourse.core.presentation.UiText
import com.spascoding.taskycourse.core.presentation.asUiText
import com.spascoding.taskycourse.feature_agenda.domain.repository.AgendaRepository
import com.spascoding.taskycourse.feature_auth.presentation.util.NameInitialsExtractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private var userInfoManager: UserInfoManager,
    private val agendaRepository: AgendaRepository,
) : ViewModel() {

    var state = MutableStateFlow(AgendaViewModelState())
        private set

    init {
        viewModelScope.launch {
            userInfoManager.userInfoFlow.collect { userInfo ->
                if (userInfo == null) {
                    return@collect
                }
                state.update {
                    it.copy(
                        username = NameInitialsExtractor(userInfo.fullName).extract(),
                    )
                }
            }
        }
    }

    private val _toastChannel = Channel<UserEvent>(Channel.BUFFERED)
    val toastMessages: Flow<UserEvent> = _toastChannel.receiveAsFlow()

    fun onEvent(event: AgendaEvent) {
        when (event) {
            is AgendaEvent.LogoutAction -> {
                viewModelScope.launch {
                    agendaRepository.logout()
                        .onSuccess {
                            _toastChannel.send(UserEvent.Error(UiText.StringResource(R.string.logged_out)))
                        }
                        .onError { error ->
                            val errorMassage = error.asUiText()
                            _toastChannel.send(UserEvent.Error(errorMassage))
                        }
                }
            }
            is AgendaEvent.SelectCalendarDateAction -> {
                state.update {
                    it.copy(
                        calendarDate = event.date,
                        selectedDate = event.date,
                    )
                }
            }
            is AgendaEvent.SelectDateAction -> {
                state.update {
                    it.copy(
                        selectedDate = event.date,
                    )
                }
            }
            is AgendaEvent.SelectAgendaItemMenu -> {

            }
            is AgendaEvent.SelectAgendaDone -> {

            }
        }
    }

    sealed interface UserEvent {
        data class Error(val error: UiText) : UserEvent
    }

}