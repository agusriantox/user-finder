package com.github.userfinder.data.remote

import com.github.userfinder.data.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {

    @GET("/search/users")
    suspend fun searchUser(
        @Query("q") name:String?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?
    ): SearchResponse

}