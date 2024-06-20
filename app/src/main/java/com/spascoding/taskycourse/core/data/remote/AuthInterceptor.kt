package com.spascoding.taskycourse.core.data.remote

import android.util.Log
import com.spascoding.taskycourse.core.data.local.UserInfo
import com.spascoding.taskycourse.core.data.local.UserInfoManager
import com.spascoding.taskycourse.core.data.remote.model.RefreshTokenRequest
import com.spascoding.taskycourse.core.data.remote.model.RefreshTokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userInfoManager: UserInfoManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val userInfo = runBlocking {
            return@runBlocking userInfoManager.userInfoFlow.first()
        }

        val requestBuilder = chain.request().newBuilder()

        if (userInfo != null && System.currentTimeMillis() > userInfo.accessTokenExpirationTimestamp) {
            Log.d("AuthInterceptor", "accessToken = ${userInfo.accessToken}")

            val refreshedToken = refreshToken(userInfo)

            // Create a new request with the refreshed access token
            val newRequest = requestBuilder
                .header("Authorization", "Bearer $refreshedToken")
                .build()

            // Retry the request with the new access token
            return chain.proceed(newRequest)
        }

        if (userInfo != null) {
            requestBuilder.addHeader("Authorization", "Bearer ${userInfo.accessToken}")
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun refreshToken(userInfo: UserInfo): String {
        val refreshedToken = runBlocking(Dispatchers.IO) {
            val request = RefreshTokenRequest(userInfo.refreshToken, userInfo.userId)
            val response = RetrofitTokenClient.api.refreshAccessToken(request)
            val body = response.body()
            if (body != null) {
                val resultUserInfo = userInfo.copy(
                    accessToken = body.accessToken,
                    accessTokenExpirationTimestamp = body.expirationTimestamp,
                )
                userInfoManager.saveUserInfo(resultUserInfo)
                resultUserInfo.accessToken
            } else {
                userInfo.accessToken
            }
        }
        Log.d("AuthInterceptor", "refreshedToken = $refreshedToken")
        return refreshedToken
    }

    private object RetrofitTokenClient {
        private const val BASE_URL = "https://tasky.pl-coding.com/"

        private val client = OkHttpClient.Builder()
            .addInterceptor(ApiKeyInterceptor())
            .build()

        val instance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api: TaskyApiService by lazy {
            instance.create(TaskyApiService::class.java)
        }
    }

    private interface TaskyApiService {
        @POST("/accessToken")
        suspend fun refreshAccessToken(@Body request: RefreshTokenRequest): retrofit2.Response<RefreshTokenResponse>

        /*
        I don't know how to use this
        Check authentication
        Endpoint: /authenticate
        Method: GET
        Description: Used to check if a token is (still) valid
        Response: 200 if valid, 401 if invalid
         */
//        @GET("/authenticate")
//        suspend fun authenticate(
//            @Header("Authorization") token: String
//        ): retrofit2.Response<Void>
    }

}