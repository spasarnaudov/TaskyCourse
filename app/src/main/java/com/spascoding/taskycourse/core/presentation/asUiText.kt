package com.spascoding.taskycourse.core.presentation

import com.spascoding.taskycourse.R
import com.spascoding.taskycourse.core.data.Result
import com.spascoding.taskycourse.core.domain.DataError

fun DataError.Remote.asUiText(): UiText {
    val error = when(this) {
        DataError.Remote.BAR_REQUEST -> R.string.error_400
        DataError.Remote.UNAUTHORIZED -> R.string.error_401
        DataError.Remote.FORBIDDEN -> R.string.error_403
        DataError.Remote.NOT_FOUND -> R.string.error_404
        DataError.Remote.METHOD_NOT_ALLOWED -> R.string.error_405
        DataError.Remote.REQUEST_TIMEOUT -> R.string.error_408
        DataError.Remote.PAYLOAD_TOO_LARGE -> R.string.error_413

        DataError.Remote.INTERNAL_SERVER_ERROR -> R.string.error_500
        DataError.Remote.BAD_GATEWAY -> R.string.error_502
        DataError.Remote.SERVICE_UNAVAILABLE -> R.string.error_503
        DataError.Remote.GATEWAY_TIMEOUT -> R.string.error_504

        DataError.Remote.NO_INTERNET -> R.string.no_internet_connection
        DataError.Remote.SOMETHING_WENT_WRONG -> R.string.something_went_wrong
        DataError.Remote.UNKNOWN -> R.string.unknown_error
    }
    return UiText.StringResource(error)
}

fun Result.Error<DataError.Remote>.asErrorUiText(): UiText {
    return error.asUiText()
}