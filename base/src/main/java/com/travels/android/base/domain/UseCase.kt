package com.travels.android.base.domain

import io.reactivex.Single

interface UseCase<in P, R> {
    fun execute(param: P): Single<R>
}