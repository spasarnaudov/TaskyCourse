package com.spascoding.taskycourse.feature_register_screen.data

import com.spascoding.taskycourse.core.util.ConfigProperties
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object TaskyClient {
    private const val BASE_URL = "https://tasky.pl-coding.com/"
    val API_KEY = ConfigProperties.getProperty("API_KEY")

    private val client: OkHttpClient
        get() = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request: Request = chain.request().newBuilder()
                    .addHeader("x-api-key", API_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}