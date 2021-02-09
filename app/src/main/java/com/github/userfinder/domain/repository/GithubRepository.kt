package com.github.userfinder.domain.repository

import com.github.userfinder.data.request.SearchRequest
import com.github.userfinder.data.response.SearchResponse

interface GithubRepository {

    suspend fun searchUser(request : SearchRequest?): SearchResponse

}