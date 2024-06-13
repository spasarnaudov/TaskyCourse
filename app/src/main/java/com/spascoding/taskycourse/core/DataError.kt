package com.spascoding.taskycourse.core

import com.spascoding.taskycourse.core.domain.util.Error

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