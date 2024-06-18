package com.spascoding.taskycourse.core.data.util

import com.spascoding.taskycourse.core.data.Result
import com.spascoding.taskycourse.core.domain.DataError
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

object RequestHelper {
    suspend fun <T> performRequest(
        request: suspend () -> Response<T>,
        onSuccess: suspend (T?) -> Unit = {}
    ): Result<T?, DataError.Remote> {
        return try {
            val response = request()
            if (response.isSuccessful) {
                val body = response.body() ?: throw IllegalStateException("Expected a response body")
                onSuccess(body)
                Result.Success(body)
            } else {
                handleErrorCode(response.code())
            }
        } catch (e: HttpException) {
            handleErrorCode(e.code())
        } catch (e: IOException) {
            Result.Error(DataError.Remote.NO_INTERNET)
        }
    }

    private fun handleErrorCode(code: Int): Result.Error<DataError.Remote> {
        return when (code) {
            400 -> Result.Error(DataError.Remote.BAR_REQUEST)
            401 -> Result.Error(DataError.Remote.UNAUTHORIZED)
            403 -> Result.Error(DataError.Remote.FORBIDDEN)
            404 -> Result.Error(DataError.Remote.NOT_FOUND)
            405 -> Result.Error(DataError.Remote.METHOD_NOT_ALLOWED)
            408 -> Result.Error(DataError.Remote.REQUEST_TIMEOUT)
            413 -> Result.Error(DataError.Remote.PAYLOAD_TOO_LARGE)

            500, 502, 503, 504 -> Result.Error(DataError.Remote.SOMETHING_WENT_WRONG)

            else -> Result.Error(DataError.Remote.UNKNOWN)
        }
    }
}