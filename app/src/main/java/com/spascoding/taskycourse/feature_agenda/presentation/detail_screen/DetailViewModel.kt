package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.navigation.Navigation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private var _state = MutableStateFlow(DetailViewModelState())
    val state = _state.asStateFlow()

    init {
        val navigationString = savedStateHandle.get<String>("navigation")
        val navigation = navigationString?.let { Navigation.fromRoute(it) }
        navigation?.let { setNavigation(it) }
    }

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

    fun setNavigation(navigation: Navigation) {
        savedStateHandle.set("navigation", navigation.route)
        when (navigation) {
            Navigation.EventDetailNavigation -> {
                _state.update { it.copy(title = R.string.new_event, description = R.string.event_description) }
            }
            Navigation.RemainderDetailNavigation -> {
                _state.update { it.copy(title = R.string.new_reminder, description = R.string.reminder_description) }
            }
            Navigation.TaskDetailNavigation -> {
                _state.update { it.copy(title = R.string.new_task, description = R.string.task_description) }
            }
            else -> {}
        }
    }
}