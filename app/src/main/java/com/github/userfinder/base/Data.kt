package com.github.userfinder.base

data class Data<T>(var state: State, var data: T? = null, var error: Error? = null)