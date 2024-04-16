package com.example.techiebutlertest.domain.util

sealed class AppResult<T> {
    data class Success<T>(val data: T) : AppResult<T>()
    data class Error<T>(val error: Throwable) : AppResult<T>()
}

inline fun <T, R> AppResult<T>.getResult(
    success: (AppResult.Success<T>) -> R,
    error: (AppResult.Error<T>) -> R
): R = when (this) {
    is AppResult.Success -> success(this)
    is AppResult.Error -> error(this)
}