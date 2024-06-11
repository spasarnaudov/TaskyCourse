package com.spascoding.taskycourse.feature_register_screen.domain.use_case

import android.util.Log
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfo
import com.spascoding.taskycourse.feature_register_screen.data.local.model.UserInfoManager
import com.spascoding.taskycourse.feature_register_screen.data.remote.AuthenticationApi
import com.spascoding.taskycourse.feature_register_screen.data.remote.model.LoginRequest
import com.spascoding.taskycourse.feature_register_screen.data.remote.model.LoginResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginUserUseCase @Inject constructor(
    private val authenticationApi: AuthenticationApi,
    private val userInfoManager: UserInfoManager,
) {

    @OptIn(DelicateCoroutinesApi::class)
    operator fun invoke(
        email: String,
        password: String,
        onSuccess: (LoginResponse) -> Unit = {}
    ) {
        val request = LoginRequest(email, password)
        authenticationApi.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body() as LoginResponse
                    val userInfo = UserInfo(
                        email = email,
                        password = password,
                        accessToken = loginResponse.accessToken,
                        refreshToken = loginResponse.refreshToken,
                        fullName = loginResponse.fullName,
                        userId = loginResponse.userId,
                        accessTokenExpirationTimestamp = loginResponse.accessTokenExpirationTimestamp,
                    )
                    GlobalScope.launch {
                        userInfoManager.saveUserInfo(userInfo)
                        runBlocking(Dispatchers.Main) {
                            Log.d(TAG, "Login successful, response: $loginResponse")
                            onSuccess.invoke(response.body() as LoginResponse)
                        }
                    }
                } else {
                    Log.e(TAG, "Login failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "Login error: ${t.message}")
            }
        })
    }

}