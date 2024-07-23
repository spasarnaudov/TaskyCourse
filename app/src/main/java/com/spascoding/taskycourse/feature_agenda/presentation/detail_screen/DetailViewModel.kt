package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen

import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(DetailViewModelState())
    val state = _state.asStateFlow()

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.CloseAction -> {

            }
            DetailEvent.DeleteAction -> {

            }
            DetailEvent.EditAction -> {
                _state.value = state.value.copy(isEditMode = true)

            }
            DetailEvent.SaveAction -> {
                _state.value = state.value.copy(isEditMode = false)
            }
            DetailEvent.PopBackStack -> {}
            DetailEvent.EditTitleClick -> {}
            DetailEvent.EditDescriptionClick -> {}
        }
    }

    fun setNavigation(navigation: Navigation) = when (navigation) {
        Navigation.EventDetailNavigation -> {
            _state.value = state.value.copy(title = R.string.new_event)
            _state.value = state.value.copy(description = R.string.event_description)
        }
        Navigation.RemainderDetailNavigation -> {
            _state.value = state.value.copy(title = R.string.new_reminder)
            _state.value = state.value.copy(description = R.string.reminder_description)
        }
        Navigation.TaskDetailNavigation -> {
            _state.value = state.value.copy(title = R.string.new_task)
            _state.value = state.value.copy(description = R.string.task_description)
        }
        else -> {}
    }
}