package com.spascoding.taskycourse.core.domain

sealed interface DataError: Error {
    enum class Remote: DataError {
        NOT_FOUND,
        NO_INTERNET,
        UNAUTHORIZED,
    }

    enum class Local: DataError {
        DISK_FULL,
    }
}