package com.spascoding.taskycourse.core.domain

sealed interface DataError: Error {

    enum class Remote: DataError {
        BAR_REQUEST,
        UNAUTHORIZED,
        FORBIDDEN,
        NOT_FOUND,
        METHOD_NOT_ALLOWED,
        REQUEST_TIMEOUT,
        PAYLOAD_TOO_LARGE,

        INTERNAL_SERVER_ERROR,
        BAD_GATEWAY,
        SERVICE_UNAVAILABLE,
        GATEWAY_TIMEOUT,

        NO_INTERNET,
        SOMETHING_WENT_WRONG,
        UNKNOWN,
    }

    enum class Local: DataError {
        DISK_FULL,
    }

}