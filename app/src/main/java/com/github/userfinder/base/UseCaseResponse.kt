package com.github.userfinder.base

interface UseCaseResponse<Type> {

    fun onSuccess(result: Type)

    fun onError(e: Throwable?)
}