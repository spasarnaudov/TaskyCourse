package com.spascoding.taskycourse.feature_register_screen.domain.use_case

import android.util.Log
import com.spascoding.taskycourse.feature_register_screen.data.remote.AuthenticationApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class LogoutUserUseCase @Inject constructor(
    private val authenticationApi: AuthenticationApi
) {

    operator fun invoke(
        token: String,
        onSuccess: () -> Unit,
    ) {
        authenticationApi.logout("Bearer $token").enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess.invoke()
                    Log.d(TAG, "Logout successful")
                } else {
                    Log.e(TAG, "Logout failed: ${response.code()}")
                    Log.e(TAG, "Logout failed: ${response.errorBody()?.string()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e(TAG, "Logout error: ${t.message}")
            }
        })
    }

}