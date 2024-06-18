package com.spascoding.taskycourse.feature_agenda.data.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface AgendaApi {
    @GET("/logout")
    suspend fun logout(): Response<Unit>
}