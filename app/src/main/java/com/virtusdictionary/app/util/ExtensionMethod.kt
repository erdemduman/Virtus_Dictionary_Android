package com.virtusdictionary.app.util

import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Throwable.toApiException(): ApiException {
    return when (this) {
        is UnknownHostException -> ApiException.NoInternetConnection
        is HttpException -> ApiException.NoSuchResult
        is SocketTimeoutException -> ApiException.Timeout
        else -> ApiException.Unknown
    }
}

