package com.spascoding.taskycourse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfo
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfoManager
import com.spascoding.taskycourse.ui.theme.MainState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private var userInfoManager: UserInfoManager
): ViewModel() {

    var state = MutableStateFlow(MainState(
        isReady = false,
        isAuthenticated = false,
    ))
        private set

    init {
        viewModelScope.launch {
            userInfoManager.userInfoFlow.collect { userInfo ->
                state.update {
                    it.copy(
                        isReady = true,
                        isAuthenticated = userInfo != UserInfo(),
                    )
                }
            }
        }
    }

}