package com.example.mvinews.data.remote

sealed class ApiFailure(message: String) {
    data class UnAuthorized(val message: String) : ApiFailure(message)
    data class noInternetConnection(val message: String) : ApiFailure(message)
}