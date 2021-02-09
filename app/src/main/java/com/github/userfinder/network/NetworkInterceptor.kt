package com.github.userfinder.network

import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newHttpUrl = request.url.newBuilder()
            .addQueryParameter("access_token", "5a9f0dab97976fcdba772675cd7e3323390529cc")
            .build()

        val builder = request.newBuilder().url(newHttpUrl)
        return chain.proceed(builder.build())
    }

}