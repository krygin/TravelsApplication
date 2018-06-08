package com.travels.android.base.domain


sealed class Response<T> {

    class Loading<T>: Response<T>() {

    }

    class Success<T>(val data: T) : Response<T>() {

    }

    class Failure<T>(val throwable: Throwable) : Response<T>() {

    }
}