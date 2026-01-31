package com.example.rickmorty.data.common

suspend inline fun <T> makeRequest(
    crossinline request: suspend () -> T
): Either<Failure, T> {
    return try {
        Either.Right(request())
    } catch (t: Throwable) {
        Either.Left(t.toFailure())
    }
}
