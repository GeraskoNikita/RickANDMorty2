package com.example.rickmorty.data.common

import retrofit2.HttpException
import java.io.IOException

fun Throwable.toFailure(): Failure = when (this) {
    is IOException -> Failure.Network(this.message)
    is HttpException -> Failure.Http("HTTP ${code()}: ${message()}")
    else -> Failure.Unknown(this.message)
}
