package com.alangeronimo.koin_exercise.domain.entity

abstract class Response<T> {
    data class Success<T>(val data: T) : Response<T>()
    data class Failure<T>(val error: Error) : Response<T>()
}