package com.example.mvinews.data.remote

import retrofit2.HttpException
import java.io.IOException

fun Throwable.getErrorMessage(): String {
    when (this) {
        is IOException -> return "no Internet Conections"
        is HttpException -> {
            when (this.code()) {
                401 -> ApiFailure.UnAuthorized("unAuthorized")
            }
        }
    }
    return return ""
}