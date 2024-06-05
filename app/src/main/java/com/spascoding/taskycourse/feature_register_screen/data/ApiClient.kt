package com.spascoding.taskycourse.feature_register_screen.data

object ApiClient {
    val apiService: ApiService by lazy {
        TaskyClient.retrofit.create(ApiService::class.java)
    }
}