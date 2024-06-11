package com.spascoding.taskycourse

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfo
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfoManager
import com.spascoding.taskycourse.ui.theme.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var userInfoManager: UserInfoManager
): ViewModel() {

    private val _state = mutableStateOf(MainState(
        userInfo = UserInfo(),
        isReady = false,
    ))
    val state: State<MainState> = _state

    init {
        viewModelScope.launch {
            userInfoManager.userInfoFlow.collect { userInfo ->
                _state.value = state.value.copy(
                    userInfo = userInfo,
                    isReady = true,
                )
            }
        }
    }

}