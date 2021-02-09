package com.github.userfinder.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class UseCase<Type, in Params>() where Type : Any {

    abstract suspend fun run(params: Params? = null): Type


    fun invoke(scope: CoroutineScope, params: Params?, onResult: UseCaseResponse<Type>?) {

        scope.launch {
            try {
                val result = run(params)
                onResult?.onSuccess(result)
            } catch (e: Exception) {
                e.printStackTrace()
                onResult?.onError(e)
            }
        }
    }

}