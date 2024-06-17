package com.spascoding.taskycourse.core.data.remote

import com.spascoding.taskycourse.core.data.local.UserInfoManager
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userInfoManager: UserInfoManager,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val userInfo = runBlocking {
            return@runBlocking userInfoManager.userInfoFlow.first()
        }

        val requestBuilder = chain.request().newBuilder()

        if (userInfo != null) {
            requestBuilder.addHeader("Authorization", "Bearer ${userInfo.accessToken}")
        }

        val request = requestBuilder.build()
        return chain.proceed(request)
    }

}