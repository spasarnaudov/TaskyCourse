package com.spascoding.taskycourse.feature_register_screen.domain.use_case

import android.util.Log
import com.spascoding.taskycourse.feature_register_screen.data.ApiClient
import com.spascoding.taskycourse.feature_register_screen.data.model.LoginRequest
import com.spascoding.taskycourse.feature_register_screen.data.model.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LoginUserUseCase @Inject constructor() {

    operator fun invoke(
        email: String,
        password: String,
        onSuccess: (String) -> Unit = {}
    ) {
        val request = LoginRequest(email, password)
        ApiClient.apiService.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val authToken = response.body()?.token
                    Log.d(TAG, "Login successful, token: $authToken")
                    authToken?.let { onSuccess.invoke(it) }
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