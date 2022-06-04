package com.example.mvinews.domain

sealed class Result<T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Error<T>(val error: String) : Result<T>()
}