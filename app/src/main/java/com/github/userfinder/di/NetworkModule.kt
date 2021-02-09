package com.github.userfinder.di

import com.github.userfinder.BuildConfig
import com.github.userfinder.data.remote.GithubService
import com.github.userfinder.network.NetworkInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val TIME_OUT = 30L
private const val BASE_URL = "https://api.github.com/"

val NetworkModule = module {
    single { GsonConverterFactory.create() }
    single { createOkHttpClient() }
    single { createGithubService(get(), get()) }
}


fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    if (BuildConfig.DEBUG) {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    }

    return OkHttpClient.Builder()
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(NetworkInterceptor())
        .build()
}

fun createGithubService(okHttpClient: OkHttpClient, factory: GsonConverterFactory): GithubService {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(factory)
        .build()
        .create(GithubService::class.java)
}