package com.spascoding.taskycourse.feature_agenda.presentation.agenda

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.feature_agenda.domain.repository.AgendaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendaViewModel @Inject constructor(
    private val agendaRepository: AgendaRepository,
) : ViewModel() {

    fun onEvent(event: AgendaEvent) {
        when (event) {
            is AgendaEvent.LogoutAction -> {
                viewModelScope.launch {
                    agendaRepository.logout()
                }
            }
        }
    }

}