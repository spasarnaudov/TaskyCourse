package com.spascoding.taskycourse.feature_register_screen.domain.use_case

import android.util.Log
import com.spascoding.taskycourse.feature_register_screen.data.remote.AuthenticationApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class AuthenticateUserUseCase @Inject constructor(
    private val authenticationApi: AuthenticationApi
) {

    operator fun invoke(
        token: String,
        onSuccess: () -> Unit,
    ) {
        authenticationApi.authenticate(token).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    onSuccess.invoke()
                    Log.d(TAG, "Authentication successful")
                } else {
                    Log.e(TAG, "Authentication failed: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e(TAG, "Authentication error: ${t.message}")
            }
        })
    }

}