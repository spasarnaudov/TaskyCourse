package com.spascoding.taskycourse.feature_agenda.presentation.detail_screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class DetailViewModel @Inject constructor() : ViewModel() {

    var state = MutableStateFlow(DetailViewModelState())
        private set

    fun onEvent(event: DetailEvent) {
        when (event) {
            DetailEvent.CloseAction -> {

            }
            DetailEvent.DeleteAction -> {

            }
            DetailEvent.EditAction -> {
                state.update {
                    state.value.copy(isEditMode = true)
                }
            }
            DetailEvent.SaveAction -> {
                state.update {
                    state.value.copy(isEditMode = false)
                }
            }
            DetailEvent.PopBackStack -> {}
        }
    }
}