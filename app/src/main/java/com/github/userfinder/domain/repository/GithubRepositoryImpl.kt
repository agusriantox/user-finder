package com.github.userfinder.domain.repository

import com.github.userfinder.data.remote.GithubService
import com.github.userfinder.data.request.SearchRequest
import com.github.userfinder.data.response.SearchResponse

class GithubRepositoryImpl(private val service: GithubService) : GithubRepository {

    override suspend fun searchUser(request: SearchRequest?): SearchResponse {
        return service.searchUser(
            name = request?.name,
            page = request?.page,
            perPage = request?.perPage
        )

    }


}