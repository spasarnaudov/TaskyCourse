package com.spascoding.taskycourse.feature_register_screen.domain.use_case

import android.util.Log
import com.spascoding.taskycourse.feature_register_screen.data.ApiClient
import com.spascoding.taskycourse.feature_register_screen.data.model.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor() {

    operator fun invoke(
        name: String,
        email: String,
        password: String,
        onSuccess: (String, String) -> Unit,
    ) {
        val request = RegisterRequest(name, email, password)
        ApiClient.apiService.register(request).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Log.d(TAG, "Registration successful")
                    onSuccess.invoke(email, password)
                } else {
                    Log.e(TAG, "Registration failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e(TAG, "Registration error: ${t.message}")
            }
        })
    }

}